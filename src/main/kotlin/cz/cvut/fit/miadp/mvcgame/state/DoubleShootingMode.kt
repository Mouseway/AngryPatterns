package cz.cvut.fit.miadp.mvcgame.state

import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon

class DoubleShootingMode : IShootingMode {
    override fun getName(): String = "Double shooting mode"

    override fun shoot(cannon: AbsCannon) {
        cannon.aimUp()
        cannon.primitiveShoot()
        cannon.aimDown()
        cannon.aimDown()
        cannon.primitiveShoot()
        cannon.aimUp()
    }
}