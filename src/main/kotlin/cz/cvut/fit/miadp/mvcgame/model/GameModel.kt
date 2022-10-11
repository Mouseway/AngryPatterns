package cz.cvut.fit.miadp.mvcgame.model

import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectsFactoryA
import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA.CollisionA
import cz.cvut.fit.miadp.mvcgame.observer.IObserver
import cz.cvut.fit.miadp.mvcgame.strategy.generateEnemiesStrategy.BlockOfEnemiesStrategy
import cz.cvut.fit.miadp.mvcgame.strategy.generateEnemiesStrategy.IGenerateEnemiesStrategy
import cz.cvut.fit.miadp.mvcgame.strategy.generateEnemiesStrategy.TriangleOfEnemiesStrategy
import cz.cvut.fit.miadp.mvcgame.strategy.movingStrategy.IMovingStrategy
import cz.cvut.fit.miadp.mvcgame.strategy.movingStrategy.RealisticMovingStrategy
import cz.cvut.fit.miadp.mvcgame.strategy.movingStrategy.SimpleMovingStrategy
import java.util.*

open class GameModel : IGameModel {

    private val goFactory = GameObjectsFactoryA(this)
    private var generateEnemiesStrategy: IGenerateEnemiesStrategy = TriangleOfEnemiesStrategy()

    private val cannon = goFactory.createCannon()
    private val missiles: MutableList<AbsMissile> = mutableListOf()
    private val enemies: MutableList<AbsEnemy> = generateEnemiesStrategy.generateEnemies(goFactory).toMutableList()
    private val collisions: MutableList<AbsCollision> = mutableListOf()

    private val observers: MutableList<IObserver> = mutableListOf()
    private var score: Int = 0
    private var movingStrategy: IMovingStrategy = SimpleMovingStrategy()
    private val unexecutedCommands: Queue<AbstractGameCommand> = LinkedList()
    private val executedCommands: Stack<AbstractGameCommand> = Stack()


    override fun update() {
        executeCommands()
        checkCollisions()
        if(levelCompleted())
            createLevel()
        this.moveMissiles()
    }

    private fun executeCommands() {
        while (unexecutedCommands.isNotEmpty()){
            val cmd = unexecutedCommands.poll()
            cmd.doExecute()
            executedCommands.add(cmd)
        }
    }

    override fun getCannonPosition(): Position = cannon.position
    override fun registerCommand(command: AbstractGameCommand) {
        unexecutedCommands.add(command)
    }

    override fun undoLastCommand() {
        if(executedCommands.isNotEmpty()){
            val cmd = executedCommands.pop()
            cmd.unExecute()
        }
    }

    fun createLevel(){
        toggleGenerateStrategy()
        enemies.clear()
        enemies.addAll(generateEnemiesStrategy.generateEnemies(goFactory))
    }
    override fun moveCannonUp() {
        cannon.moveUp()
        notifyObservers()
    }

    override fun moveCannonDown() {
        cannon.moveDown()
        notifyObservers()
    }

    override fun registerObserver(obs: IObserver) {
        if(!observers.contains(obs))
            observers.add(obs)
    }

    override fun unregisterObserver(obs: IObserver) {
        if(observers.contains(obs))
            observers.remove(obs)
    }

    override fun notifyObservers() {
        observers.forEach { it.update() }
    }

    override fun cannonShoot(){
        this.missiles.addAll(cannon.shoot())
        notifyObservers()
    }

    private fun moveMissiles() {
        missiles.forEach {
            missile -> missile.move()
        }
        destroyMissiles()
        notifyObservers()
    }

    private fun destroyMissiles(){
        missiles.removeIf{ missile ->
            missile.position.x > MvcGameConfig.MAX_X
        }
    }

    fun levelCompleted(): Boolean = enemies.isEmpty() && collisions.isEmpty()
    override fun getGameObjects(): List<GameObject> = listOf(cannon).plus(missiles).plus(enemies).plus(collisions).plus(getGameInfo())
    override fun getMovingStrategy(): IMovingStrategy {
        return movingStrategy
    }

    override fun createMemento(): Any {
        val m = Memento()
        m.score = score
        m.cannonAngle = cannon.angle
        m.cannonPower = cannon.power
        m.cannonX = cannon.position.x
        m.cannonY = cannon.position.y

        m.enemies = enemies.toList()
        m.missiles = missiles.toList()
        m.collisions = collisions.toList()

        return m
    }

    override fun setMemento(memento: Any) {
        val m = memento as Memento
        score = m.score
        cannon.angle = m.cannonAngle
        cannon.power = m.cannonPower
        cannon.position = Position(m.cannonX, m.cannonY)

        enemies.clear()
        enemies.addAll(m.enemies)
        collisions.clear()
        collisions.addAll(m.collisions)
        missiles.clear()
        missiles.addAll(m.missiles)
    }
    private class Memento {
        var score: Int = 0

        var cannonX: Int = 0
        var cannonY: Int = 0
        var cannonAngle = 0.0
        var cannonPower = 0


        lateinit var enemies: List<AbsEnemy>
        lateinit var collisions: List<AbsCollision>
        lateinit var missiles: List<AbsMissile>

    }

    override fun aimCannonUp(){
        this.cannon.aimUp()
        notifyObservers()
    }

    override fun aimCannonDown(){
        this.cannon.aimDown()
        notifyObservers()
    }

    override fun cannonPowerUp(){
        cannon.powerUp()
        notifyObservers()
    }

    override fun cannonPowerDown(){
        cannon.powerDown()
        notifyObservers()
    }

    override fun toggleMovingStrategy() {
        movingStrategy = when(movingStrategy){
            is SimpleMovingStrategy -> RealisticMovingStrategy()
            else -> SimpleMovingStrategy()
        }
    }

    override fun toggleShootingMode() {
        cannon.toggleShootingMode()
    }

    fun toggleGenerateStrategy(){
        generateEnemiesStrategy = when(generateEnemiesStrategy){
            is BlockOfEnemiesStrategy -> TriangleOfEnemiesStrategy()
            else -> BlockOfEnemiesStrategy()
        }
    }

    private fun checkCollisions(){
        removeOldCollisions()
        collisions.addAll(detectNewCollisions())
    }

    private fun removeOldCollisions() {
        collisions.removeIf{c -> c.getAge()/100 > 5}
    }

    private fun detectNewCollisions(): List<AbsCollision>{
        val newCollisions = mutableListOf<AbsCollision>()
        val enemiesToDelete = mutableListOf<AbsEnemy>()
        val missilesToDelete = mutableListOf<AbsMissile>()

        missiles.forEach { missile ->
            enemies.forEach { enemy ->
                if( missile.position.x + MvcGameConfig.IMAGE_SIZE >= enemy.position.x
                    && missile.position.x <= enemy.position.x + MvcGameConfig.IMAGE_SIZE
                    && missile.position.y <= enemy.position.y + MvcGameConfig.IMAGE_SIZE
                    && missile.position.y + MvcGameConfig.IMAGE_SIZE >= enemy.position.y)   {

                    score += MvcGameConfig.ENEMY_POINTS
                    newCollisions.add(goFactory.createCollision(enemy.position))
                    missilesToDelete.add(missile)
                    enemiesToDelete.add(enemy)
                }
            }
        }

        enemies.removeAll(enemiesToDelete)
        missiles.removeAll(missilesToDelete)

        return newCollisions
    }

    private fun getGameInfo(): List<AbsGameInfo> {
        return listOf(
                goFactory.createGameInfo("Cannon angle: %.2f".format(cannon.angle), Position(30, 30)),
                goFactory.createGameInfo("Cannon power: ${cannon.power}", Position(230, 30)),
                goFactory.createGameInfo("Score: $score", Position(430, 30))
            )
    }
}