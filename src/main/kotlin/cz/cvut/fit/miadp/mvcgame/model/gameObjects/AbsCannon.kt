package cz.cvut.fit.miadp.mvcgame.model.gameObjects

import cz.cvut.fit.miadp.mvcgame.state.DoubleShootingMode
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode
import cz.cvut.fit.miadp.mvcgame.state.SingleShootingMode
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor

abstract class AbsCannon() : GameObject() {


    protected var SINGLE_SHOOTING_MODE: IShootingMode = SingleShootingMode()
    protected var DOUBLE_SHOOTING_MODE: IShootingMode = DoubleShootingMode()
    protected var shootingMode: IShootingMode = DOUBLE_SHOOTING_MODE

    abstract var angle: Double
    abstract var power: Int

    abstract fun moveUp(): Unit
    abstract fun moveDown(): Unit
    abstract fun shoot(): List<AbsMissile>
    abstract fun aimUp()
    abstract fun aimDown()
    abstract fun powerUp()
    abstract fun powerDown()
    override fun accept(visitor: IVisitor) {
        visitor.visitCannon(this)
    }

    abstract fun primitiveShoot()

    fun toggleShootingMode(){
        if (this.shootingMode is SingleShootingMode) {
            this.shootingMode = DOUBLE_SHOOTING_MODE
        } else {
            this.shootingMode = SINGLE_SHOOTING_MODE
        }
    }

    companion object{
        val SINGLE_SHOOTING_MODE = SingleShootingMode()
        val DOUBLE_SHOOTING_MODE = DoubleShootingMode()
    }

}