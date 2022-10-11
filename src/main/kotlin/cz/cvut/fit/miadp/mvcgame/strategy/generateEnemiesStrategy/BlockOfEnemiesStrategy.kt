package cz.cvut.fit.miadp.mvcgame.strategy.generateEnemiesStrategy

import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory
import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy

class BlockOfEnemiesStrategy : IGenerateEnemiesStrategy {
    override fun generateEnemies(goFactory: IGameObjectsFactory): MutableList<AbsEnemy> {
        val xOffset = 300
        val yOffset = 30
        val freeSpace = 80
        val enemies = mutableListOf<AbsEnemy>()

        for(i in 1..6)
            for (j in 1..6)
                enemies.add(goFactory.createEnemy(Position(xOffset + i * freeSpace, yOffset + j * freeSpace)))

        return enemies
    }
}