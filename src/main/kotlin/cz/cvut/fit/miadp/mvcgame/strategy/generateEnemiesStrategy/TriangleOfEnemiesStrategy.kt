package cz.cvut.fit.miadp.mvcgame.strategy.generateEnemiesStrategy

import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory
import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy

class TriangleOfEnemiesStrategy : IGenerateEnemiesStrategy {
    override fun generateEnemies(goFactory: IGameObjectsFactory): MutableList<AbsEnemy> {
        val xOffset = 300
        val yOffset = 30
        val freeSpace = 80
        val enemies = mutableListOf<AbsEnemy>()
        var tOffset = 0;
        val rows = 6
        val center = (freeSpace * rows / 2)
        for(i in rows downTo 1){
            for (j in 1..i){
                tOffset = if(j < i.toDouble() / 2)
                    (center - (i.toDouble() / 2 - j) * freeSpace).toInt();
                else
                    (center + (j - i.toDouble() / 2) * freeSpace).toInt();
                enemies.add(goFactory.createEnemy(Position(xOffset + tOffset , yOffset + i * freeSpace)))
            }
        }

        return enemies
    }
}