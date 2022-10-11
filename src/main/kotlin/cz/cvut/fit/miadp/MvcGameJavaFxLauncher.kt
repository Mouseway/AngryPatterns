package cz.cvut.fit.miadp

import cz.cvut.fit.miadp.mvcgame.MvcGame
import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.stage.Stage

class MvcGameJavaFxLauncher : Application() {
    override fun init() {
        theMvcGame.init()
    }

    override fun start(stage: Stage) {
        val winTitle: String = theMvcGame.windowTitle
        val winWidth: Int = theMvcGame.windowWidth
        val winHeight: Int = theMvcGame.windowHeight
        stage.title = winTitle
        val root = Group()
        val theScene = Scene(root)
        stage.scene = theScene
        val canvas = Canvas(winWidth.toDouble(), winHeight.toDouble())
        root.children.add(canvas)
        val gc = canvas.graphicsContext2D
        val pressedKeysCodes = ArrayList<String>()
        theScene.onKeyPressed = EventHandler { e ->
            val code = e.code.toString()

            // only add once... prevent duplicates
            if (!pressedKeysCodes.contains(code)) pressedKeysCodes.add(code)
        }
        theScene.onKeyReleased = EventHandler { e ->
            val code = e.code.toString()
            pressedKeysCodes.remove(code)
        }

        // the game-loop
        object : AnimationTimer() {
            override fun handle(currentNanoTime: Long) {
                // Clear the canvas

                theMvcGame.processPressedKeys(pressedKeysCodes)
                pressedKeysCodes.clear()
                theMvcGame.update()
                theMvcGame.render(gc)
            }
        }.start()
        stage.show()
    }

    companion object {
        private val theMvcGame: MvcGame = MvcGame()
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(MvcGameJavaFxLauncher::class.java)
        }
    }
}