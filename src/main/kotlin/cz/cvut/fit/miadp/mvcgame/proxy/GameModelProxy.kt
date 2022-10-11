package cz.cvut.fit.miadp.mvcgame.proxy

import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand
import cz.cvut.fit.miadp.mvcgame.model.IGameModel
import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.GameObject
import cz.cvut.fit.miadp.mvcgame.observer.IObserver
import cz.cvut.fit.miadp.mvcgame.strategy.movingStrategy.IMovingStrategy

class GameModelProxy(private val subject: IGameModel) : IGameModel {

    override fun moveCannonUp() {
        subject.moveCannonUp()
    }

    override fun moveCannonDown() {
        subject.moveCannonDown()
    }

    override fun cannonShoot() {
        subject.cannonShoot()
    }

    override fun aimCannonUp() {
        subject.aimCannonUp()
    }

    override fun aimCannonDown() {
        subject.aimCannonDown()
    }

    override fun cannonPowerUp() {
        subject.cannonPowerUp()
    }

    override fun cannonPowerDown() {
        subject.cannonPowerDown()
    }

    override fun toggleMovingStrategy() {
        subject.toggleMovingStrategy()
    }

    override fun toggleShootingMode() {
        subject.toggleShootingMode()
    }

    override fun update() {
        subject.update()
    }

    override fun getGameObjects(): List<GameObject> = subject.getGameObjects()


    override fun getMovingStrategy(): IMovingStrategy = subject.getMovingStrategy()

    override fun createMemento(): Any = subject.createMemento()

    override fun setMemento(memento: Any) {
        subject.setMemento(memento)
    }

    override fun getCannonPosition(): Position = subject.getCannonPosition()
    override fun registerCommand(command: AbstractGameCommand) {
        subject.registerCommand(command)
    }

    override fun undoLastCommand() {
        subject.undoLastCommand()
    }

    override fun registerObserver(obs: IObserver) {
        subject.registerObserver(obs)
    }

    override fun unregisterObserver(obs: IObserver) {
        subject.unregisterObserver(obs)
    }

    override fun notifyObservers() {
        subject.notifyObservers()
    }
}