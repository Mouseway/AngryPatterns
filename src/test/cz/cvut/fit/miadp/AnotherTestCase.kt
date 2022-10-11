package cz.cvut.fit.miadp

import cz.cvut.fit.miadp.mvcgame.command.*
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig
import cz.cvut.fit.miadp.mvcgame.model.GameModel
import cz.cvut.fit.miadp.mvcgame.model.IGameModel
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA.CannonA
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA.MissileA
import org.junit.Assert
import org.junit.Test

class AnotherTestCase {
    @Test
    fun cannonMovements(){
        val model: IGameModel = GameModel()
        model.registerCommand(ToggleShootingModeCmd(model))
        model.registerCommand(AimCannonUpCmd(model))
        model.registerCommand(CannonPowerUpCmd(model))
        model.registerCommand(MoveCannonUpCmd(model))
        model.registerCommand(CannonShootCmd(model))
        model.update()
        model.getGameObjects().forEach { it ->
            when(it){
                is CannonA -> {
                    println(MvcGameConfig.CANNON_POS_Y)
                    println(MvcGameConfig.MOVE_STEP)
                    Assert.assertEquals(MvcGameConfig.CANNON_POS_Y - MvcGameConfig.MOVE_STEP, it.position.y)
                    Assert.assertEquals(MvcGameConfig.INIT_POWER - MvcGameConfig.POWER_STEP, it.power)
                    Assert.assertEquals(MvcGameConfig.INIT_ANGLE - MvcGameConfig.ANGLE_STEP, it.angle, 0.001)
                }
                is MissileA -> {
                    Assert.assertEquals(MvcGameConfig.INIT_ANGLE - MvcGameConfig.ANGLE_STEP, it.initAngle, 0.001)
                    Assert.assertEquals(MvcGameConfig.INIT_POWER - MvcGameConfig.POWER_STEP, it.initVelocity )
                }
            }

        }
    }

    @Test
    fun doubleShooting(){
        val model: IGameModel = GameModel()
        model.registerCommand(CannonShootCmd(model))
        model.update()
        val missiles: List<MissileA> = model.getGameObjects().filter { it is MissileA } as List<MissileA>
        Assert.assertTrue(missiles.any { it.initAngle == MvcGameConfig.INIT_ANGLE + MvcGameConfig.ANGLE_STEP })
        Assert.assertTrue(missiles.any { it.initAngle == MvcGameConfig.INIT_ANGLE - MvcGameConfig.ANGLE_STEP })
    }
}