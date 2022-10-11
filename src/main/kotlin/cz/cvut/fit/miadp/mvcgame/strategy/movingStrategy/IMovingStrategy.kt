package cz.cvut.fit.miadp.mvcgame.strategy.movingStrategy

import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile

interface IMovingStrategy {
    fun updatePosition(missile: AbsMissile)
}