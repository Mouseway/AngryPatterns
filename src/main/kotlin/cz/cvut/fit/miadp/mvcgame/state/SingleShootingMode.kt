package cz.cvut.fit.miadp.mvcgame.state

import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon

class SingleShootingMode : IShootingMode{
    override fun shoot(cannon: AbsCannon) {
        cannon.primitiveShoot()
    }

    override fun getName(): String = "Single shooting mode"
}
