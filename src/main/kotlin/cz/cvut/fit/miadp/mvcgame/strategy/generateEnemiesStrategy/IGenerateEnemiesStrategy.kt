package cz.cvut.fit.miadp.mvcgame.strategy.generateEnemiesStrategy

import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectsFactoryA
import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy

interface IGenerateEnemiesStrategy {
    fun generateEnemies(goFactory: IGameObjectsFactory): List<AbsEnemy>
}