package cz.cvut.fit.miadp.mvcgame.bridge

import cz.cvut.fit.miadp.mvcgame.model.Position

interface IGameGraphics {
    fun drawImage(path: String, pos: Position)
    fun drawText(text: String, pos: Position)
    fun drawRectangle(beginPos: Position, endPos: Position)
}