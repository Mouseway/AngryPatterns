package cz.cvut.fit.miadp.mvcgame.bridge

import cz.cvut.fit.miadp.mvcgame.model.Position

class GameGraphics(private val implementor: IGameGraphicsImplementor) : IGameGraphics{
    override fun drawImage(path: String, pos: Position) {
        implementor.drawImage(path, pos)
    }

    override fun drawText(text: String, pos: Position) {
        implementor.drawText(text, pos)
    }

    override fun drawRectangle(leftTop: Position, rightBottom: Position) {
        implementor.drawLine(leftTop, Position(rightBottom.x, leftTop.y))
        implementor.drawLine(Position(rightBottom.x, leftTop.y), rightBottom)
        implementor.drawLine(leftTop, Position(leftTop.x, rightBottom.y))
        implementor.drawLine(Position(leftTop.x, rightBottom.y), rightBottom)
    }
}