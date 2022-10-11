package cz.cvut.fit.miadp.mvcgame.strategy.movingStrategy

import cz.cvut.fit.miadp.mvcgame.model.Vector
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile
import kotlin.math.cos
import kotlin.math.sin

class SimpleMovingStrategy : IMovingStrategy {
    override fun updatePosition(missile: AbsMissile) {
        val initVelocity = missile.initVelocity
        val initAngle = missile.initAngle
        val time: Long = missile.getAge() / 100

        val dX = (initVelocity * time * cos(initAngle)).toInt()
        val dY = (initVelocity * time * sin(initAngle)).toInt()
        missile.move(Vector(dX, dY))
    }
}