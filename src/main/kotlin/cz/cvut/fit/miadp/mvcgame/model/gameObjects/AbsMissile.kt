package cz.cvut.fit.miadp.mvcgame.model.gameObjects

import cz.cvut.fit.miadp.mvcgame.model.Position

abstract class AbsMissile(var initialPosition: Position,
                          var initAngle: Double,
                          var initVelocity: Int) : LifetimeLimitedGameObject(initialPosition) {
    abstract fun move()
    abstract fun moveTo(pos: Position)
}