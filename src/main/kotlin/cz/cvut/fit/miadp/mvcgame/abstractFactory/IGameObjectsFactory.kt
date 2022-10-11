package cz.cvut.fit.miadp.mvcgame.abstractFactory

import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*

interface IGameObjectsFactory {
    fun createCannon(): AbsCannon
    fun createMissile(initAngle: Double, initVelocity: Int): AbsMissile
    fun createEnemy(position: Position): AbsEnemy
    fun createGameInfo(text: String, position: Position): AbsGameInfo
    fun createCollision(position: Position): AbsCollision
}