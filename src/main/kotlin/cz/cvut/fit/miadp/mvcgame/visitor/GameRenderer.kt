package cz.cvut.fit.miadp.mvcgame.visitor

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image

class GameRenderer(private val gr: IGameGraphics) : IVisitor {

    override fun visitCannon(cannon: AbsCannon) {
        drawCannon(cannon)
    }

    override fun visitMissile(missile: AbsMissile) {
        drawMissile(missile)
    }

    override fun visitCollision(collision: AbsCollision) {
        gr.drawImage(javaClass.getResource("/images/collision.png")?.toString() ?: "", collision.position)
    }

    override fun visitEnemy(enemy: AbsEnemy) {
        gr.drawImage(javaClass.getResource("/images/enemy1.png")?.toString() ?: "", enemy.position)
    }

    override fun visitGameInfo(gameInfo: AbsGameInfo) {
        gr.drawText(gameInfo.text, gameInfo.position)
    }

    private fun drawCannon(cannon: AbsCannon){
        gr.drawImage(javaClass.getResource("/images/cannon.png")?.toString() ?: "", cannon.position)
    }

    private fun drawMissile(missile: AbsMissile){
        gr.drawImage(javaClass.getResource("/images/missile.png")?.toString() ?: "", missile.position)
    }
}