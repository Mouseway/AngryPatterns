package cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA

import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.GameObject
import cz.cvut.fit.miadp.mvcgame.strategy.movingStrategy.IMovingStrategy
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor

class MissileA(position: Position, initAngle: Double, initVelocity: Int, var movingStrategy: IMovingStrategy) : AbsMissile(position, initAngle, initVelocity){
    override fun move() {
        this.movingStrategy.updatePosition(this)
    }

    override fun moveTo(pos: Position) {
        position.x = pos.x
        position.y = pos.y
    }

    override fun clone(): GameObject {
        return MissileA(position.copy(), initAngle, initVelocity, movingStrategy)
    }

    override fun accept(visitor: IVisitor) {
        visitor.visitMissile(this)
    }
}