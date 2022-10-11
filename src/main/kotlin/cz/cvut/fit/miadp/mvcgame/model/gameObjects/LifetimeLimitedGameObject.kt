package cz.cvut.fit.miadp.mvcgame.model.gameObjects

import cz.cvut.fit.miadp.mvcgame.model.Position
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

abstract class LifetimeLimitedGameObject(override var position: Position) : GameObject() {
    private val bornAt: LocalDateTime = LocalDateTime.now()

    fun getAge(): Long = ChronoUnit.MILLIS.between(bornAt, LocalDateTime.now())
}