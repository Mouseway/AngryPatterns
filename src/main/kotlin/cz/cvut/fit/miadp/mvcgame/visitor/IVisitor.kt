package cz.cvut.fit.miadp.mvcgame.visitor

import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*

interface IVisitor {
    fun visitCannon(cannon: AbsCannon): Unit
    fun visitMissile(missile: AbsMissile): Unit
    fun visitCollision(collision: AbsCollision): Unit
    fun visitEnemy(enemy: AbsEnemy): Unit
    fun visitGameInfo(gameInfo: AbsGameInfo): Unit
}