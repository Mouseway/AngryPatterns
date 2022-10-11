package cz.cvut.fit.miadp.mvcgame.abstractFactory

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig
import cz.cvut.fit.miadp.mvcgame.model.GameModel
import cz.cvut.fit.miadp.mvcgame.model.IGameModel
import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA.*
import cz.cvut.fit.miadp.mvcgame.strategy.movingStrategy.SimpleMovingStrategy

open class GameObjectsFactoryA(val model: IGameModel) : IGameObjectsFactory{

    private val cannonPrototype = CannonA(Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this)
    private val missilePrototype = MissileA(Position(0, 0), 0.0, 0, SimpleMovingStrategy())
    private val enemyPrototype = EnemyA(Position(0, 0))
    private val gameInfoPrototype = GameInfoA("", Position(0,0))
    private val collisionPrototype = CollisionA(Position(0,0))

    override fun createCannon(): AbsCannon = cannonPrototype.clone() as CannonA

    override fun createMissile(initAngle: Double, initVelocity: Int): AbsMissile {
        val m = missilePrototype.clone() as MissileA
        m.initVelocity = initVelocity
        m.initAngle = initAngle
        m.position = model.getCannonPosition().copy()
        m.movingStrategy = model.getMovingStrategy()
        return m
    }

    override fun createEnemy(position: Position): AbsEnemy {
        val e = enemyPrototype.clone() as EnemyA
        e.position = position.copy()
        return e
    }

    override fun createGameInfo(text: String, position: Position): AbsGameInfo {
        val g = gameInfoPrototype.clone() as GameInfoA
        g.text = text
        g.position = position.copy()
        return g
    }

    override fun createCollision(position: Position): AbsCollision {
        val c = collisionPrototype.clone() as CollisionA
        c.position = position.copy()
        return c
    }
}