package cz.cvut.fit.miadp.mvcgame.model.gameObjects

import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor

abstract class AbsCollision(position: Position) : LifetimeLimitedGameObject(position) {
    override fun accept(visitor: IVisitor) {
        visitor.visitCollision(this)
    }
}