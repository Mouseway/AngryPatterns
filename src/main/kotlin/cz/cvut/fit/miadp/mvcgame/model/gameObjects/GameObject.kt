package cz.cvut.fit.miadp.mvcgame.model.gameObjects

import cz.cvut.fit.miadp.mvcgame.model.GameModel
import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.model.Vector
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitable

abstract class GameObject() : IVisitable {
    open var position = Position()

    abstract fun clone(): GameObject

    fun move(v: Vector) {
        this.position.add(v)
    }
}