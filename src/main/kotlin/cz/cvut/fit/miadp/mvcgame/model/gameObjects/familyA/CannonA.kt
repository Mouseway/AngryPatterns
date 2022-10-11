package cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA

import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig
import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.model.Vector
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.GameObject

class CannonA(override var position: Position, val goFact: IGameObjectsFactory) : AbsCannon(){

    override var angle: Double = MvcGameConfig.INIT_ANGLE;
    override var power: Int = MvcGameConfig.INIT_POWER
    private val shootingBatch: MutableList<AbsMissile> = mutableListOf()

    override fun moveUp(){
        move(Vector(0, -MvcGameConfig.MOVE_STEP))
    }

    override fun moveDown(){
        move(Vector(0, MvcGameConfig.MOVE_STEP))
    }

    override fun shoot(): List<AbsMissile> {
        shootingBatch.clear()
        shootingMode.shoot(this)
        return shootingBatch;
    }

    override fun aimUp() {
       this.angle -= MvcGameConfig.ANGLE_STEP
    }

    override fun aimDown() {
        angle += MvcGameConfig.ANGLE_STEP
    }

    override fun powerUp() {
        power -= MvcGameConfig.POWER_STEP
    }

    override fun powerDown() {
        power += MvcGameConfig.POWER_STEP
    }

    override fun primitiveShoot() {
        shootingBatch.add(goFact.createMissile(angle, power))
    }

    override fun clone(): GameObject {
        return CannonA(position.copy(), goFact)
    }
}