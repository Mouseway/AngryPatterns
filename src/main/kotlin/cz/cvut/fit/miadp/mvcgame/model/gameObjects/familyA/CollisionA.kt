package cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA

import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCollision
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.GameObject

class CollisionA(position: Position) : AbsCollision(position) {
    override fun clone(): GameObject {
        return CollisionA(position.copy())
    }
}