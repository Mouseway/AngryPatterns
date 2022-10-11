package cz.cvut.fit.miadp.mvcgame.state

import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon

interface IShootingMode {
    fun getName(): String
    fun shoot(cannon: AbsCannon)
}