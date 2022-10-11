package cz.cvut.fit.miadp.mvcgame.bridge

import cz.cvut.fit.miadp.mvcgame.model.Position

interface IGameGraphicsImplementor {
    fun drawImage(path: String, pos: Position)
    fun drawText(text: String, pos: Position)
    fun drawLine(beginPos: Position, endPos: Position)
    fun clear()
}