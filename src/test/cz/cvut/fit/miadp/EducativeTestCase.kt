package cz.cvut.fit.miadp

import cz.cvut.fit.miadp.mvcgame.command.*
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig
import cz.cvut.fit.miadp.mvcgame.model.GameModel
import cz.cvut.fit.miadp.mvcgame.model.IGameModel
import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA.CannonA
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA.MissileA
import org.junit.Assert
import org.junit.Test


class EducativeTestCase {
    @Test
    fun undoLastCommand() {
        val model: IGameModel = GameModel()
        val positionBeforeUndoX = model.getCannonPosition().x
        val positionBeforeUndoY = model.getCannonPosition().y
        model.registerCommand(MoveCannonUpCmd(model))
        model.update()
        val positionAfterExecutionX = model.getCannonPosition().x
        val positionAfterExecutionY = model.getCannonPosition().y
        model.undoLastCommand()
        val positionAfterUndoX = model.getCannonPosition().x
        val positionAfterUndoY = model.getCannonPosition().y
        Assert.assertEquals(positionBeforeUndoX, positionAfterExecutionX)
        Assert.assertEquals(positionAfterUndoX, positionAfterExecutionX)
        Assert.assertEquals(positionBeforeUndoY, positionAfterExecutionY + MvcGameConfig.MOVE_STEP)
        Assert.assertEquals(positionBeforeUndoX, positionAfterUndoX)
        Assert.assertEquals(positionBeforeUndoY, positionAfterUndoY)
    }
}
