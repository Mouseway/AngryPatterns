package cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA

import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsGameInfo
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.GameObject
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor

class GameInfoA(override var text: String, override var position: Position) : AbsGameInfo(text) {
    override fun clone(): GameObject {
        return GameInfoA(text, position.copy())
    }

    override fun accept(visitor: IVisitor) {
        visitor.visitGameInfo(this)
    }
}