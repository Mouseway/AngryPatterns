package cz.cvut.fit.miadp.mvcgame.bridge

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig
import cz.cvut.fit.miadp.mvcgame.model.Position
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image

class JavaFxGraphics(val gr: GraphicsContext): IGameGraphicsImplementor {
    override fun drawImage(path: String, pos: Position) {
        val img: Image = Image(path)
        gr.drawImage(img, pos.x.toDouble(), pos.y.toDouble())
    }

    override fun drawText(text: String, pos: Position) {
        gr.fillText(text, pos.x.toDouble(), pos.y.toDouble())
    }

    override fun drawLine(beginPos: Position, endPos: Position) {
        gr.strokeLine(beginPos.x.toDouble(), beginPos.y.toDouble(), endPos.x.toDouble(), endPos.y.toDouble())
    }

    override fun clear() {
        gr.clearRect(0.0, 0.0, MvcGameConfig.MAX_X.toDouble(), MvcGameConfig.MAX_Y.toDouble())
    }
}