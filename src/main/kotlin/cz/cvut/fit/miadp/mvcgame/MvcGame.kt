package cz.cvut.fit.miadp.mvcgame
// in future, use Bridge to remove this dependency
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig
import cz.cvut.fit.miadp.mvcgame.controller.GameController
import cz.cvut.fit.miadp.mvcgame.model.GameModel
import cz.cvut.fit.miadp.mvcgame.model.IGameModel
import cz.cvut.fit.miadp.mvcgame.proxy.GameModelProxy
import cz.cvut.fit.miadp.mvcgame.view.GameView
import javafx.scene.canvas.GraphicsContext

class MvcGame {
    private val model: IGameModel = GameModelProxy(GameModel());
    private val view: GameView = GameView(model);
    private val controller: GameController = view.getController();

    fun init() {
    }

    fun processPressedKeys(pressedKeysCodes: List<String?>) {
      controller.processPressedKeys(pressedKeysCodes)
    }

    fun update() {
        this.model.update()
    }

    fun render(gr: GraphicsContext) {
        this.view.setGraphicContext(gr)
    }

    val windowTitle: String
        get() = "The MI-ADP.16 MvcGame"
    val windowWidth: Int
        get() = MvcGameConfig.MAX_X
    val windowHeight: Int
        get() = MvcGameConfig.MAX_Y
}