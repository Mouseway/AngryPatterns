package cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA

import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.GameObject
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor

open class EnemyA(override var position: Position) : AbsEnemy() {
    override fun clone(): GameObject {
        return EnemyA(position.copy())
    }

    override fun accept(visitor: IVisitor) {
        visitor.visitEnemy(this)
    }
}