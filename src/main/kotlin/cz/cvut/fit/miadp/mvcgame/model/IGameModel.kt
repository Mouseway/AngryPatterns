package cz.cvut.fit.miadp.mvcgame.model

import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.GameObject
import cz.cvut.fit.miadp.mvcgame.observer.IObservable
import cz.cvut.fit.miadp.mvcgame.strategy.movingStrategy.IMovingStrategy

interface IGameModel : IObservable {
    fun moveCannonUp()
    fun moveCannonDown()
    fun cannonShoot()
    fun aimCannonUp()
    fun aimCannonDown()
    fun cannonPowerUp()
    fun cannonPowerDown()
    fun toggleMovingStrategy()
    fun toggleShootingMode()

    fun update()
    fun getGameObjects(): List<GameObject>
    fun getMovingStrategy(): IMovingStrategy
    fun createMemento(): Any
    fun setMemento(memento: Any)
    fun getCannonPosition(): Position
    fun registerCommand(command: AbstractGameCommand)
    fun undoLastCommand()
}