package cz.cvut.fit.miadp.mvcgame.controller

import cz.cvut.fit.miadp.mvcgame.command.*
import cz.cvut.fit.miadp.mvcgame.memento.Caretaker
import cz.cvut.fit.miadp.mvcgame.model.IGameModel

class GameController(private val model: IGameModel) {
    fun processPressedKeys(pressedKeysCodes: List<String?>){
        for (code in pressedKeysCodes) {
            when (code) {
                "UP" -> model.registerCommand(MoveCannonUpCmd(model))
                "DOWN" -> model.registerCommand(MoveCannonDownCmd(model))
                "SPACE" -> model.registerCommand(CannonShootCmd(model))
                "A" -> model.registerCommand(AimCannonUpCmd(model))
                "Y" -> model.registerCommand(AimCannonDownCmd(model))
                "F" -> model.registerCommand(CannonPowerUpCmd(model))
                "D" -> model.registerCommand(CannonPowerDownCmd(model))
                "M" -> model.registerCommand(ToggleMovingStrategyCmd(model))
                "N" -> model.registerCommand(ToggleShootingModeCmd(model))
                "Z" -> model.undoLastCommand()
            }
        }
    }
}