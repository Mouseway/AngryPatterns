package cz.cvut.fit.miadp.mvcgame.view

import cz.cvut.fit.miadp.mvcgame.bridge.GameGraphics
import cz.cvut.fit.miadp.mvcgame.bridge.JavaFxGraphics
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig
import cz.cvut.fit.miadp.mvcgame.controller.GameController
import cz.cvut.fit.miadp.mvcgame.model.GameModel
import cz.cvut.fit.miadp.mvcgame.model.IGameModel
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile
import cz.cvut.fit.miadp.mvcgame.observer.IObserver
import cz.cvut.fit.miadp.mvcgame.visitor.GameRenderer
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image

class GameView(private val model: IGameModel) : IObserver {
    private val controller: GameController = GameController(model)
    private var updateCnt: Int = 1
    private lateinit var gr: GraphicsContext
    private lateinit var renderer: GameRenderer

    fun getController() = controller

    init {
       this.model.registerObserver(this)
    }
    fun render(): Unit {
        if(gr == null)
            return;
        if(updateCnt > 0){
            gr!!.clearRect(0.0, 0.0, MvcGameConfig.MAX_X.toDouble(), MvcGameConfig.MAX_Y.toDouble());
            model.getGameObjects().forEach{
                obj -> obj.accept(renderer)
            }
            this.updateCnt = 0
        }
    }

    fun setGraphicContext(gr: GraphicsContext){
        this.gr = gr
        renderer = GameRenderer(GameGraphics(JavaFxGraphics(gr)))
        this.render()
    }
    override fun update() {
        this.updateCnt++;
    }
}