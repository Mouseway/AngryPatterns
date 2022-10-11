package cz.cvut.fit.miadp.mvcgame.command

import cz.cvut.fit.miadp.mvcgame.model.IGameModel

class AimCannonUpCmd(val model: IGameModel) : AbstractGameCommand(model){
    override fun execute() {
        model.aimCannonUp()
    }
}