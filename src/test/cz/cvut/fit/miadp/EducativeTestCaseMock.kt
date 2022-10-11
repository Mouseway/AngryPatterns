package cz.cvut.fit.miadp

import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectsFactoryA
import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory
import cz.cvut.fit.miadp.mvcgame.model.GameModel
import cz.cvut.fit.miadp.mvcgame.model.IGameModel
import cz.cvut.fit.miadp.mvcgame.model.Position
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA.EnemyA
import cz.cvut.fit.miadp.mvcgame.strategy.generateEnemiesStrategy.BlockOfEnemiesStrategy
import cz.cvut.fit.miadp.mvcgame.strategy.movingStrategy.SimpleMovingStrategy
import javafx.geometry.Pos
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


open class EducativeTestCaseMock {
    @Test
    fun createMissile() {
        val model: IGameModel = mock(GameModel::class.java)
        `when`(model.getCannonPosition()).thenReturn(Position(500, 600))
        `when`(model.getMovingStrategy()).thenReturn(SimpleMovingStrategy())
        val goFact: IGameObjectsFactory = GameObjectsFactoryA(model)
        val missile = goFact.createMissile(0.0, 10000000)
        Assert.assertEquals(missile.position.x, 500)
        Assert.assertEquals(missile.position.y, 600)
    }

    @Test
    fun generateLevel(){
        val goFactory: IGameObjectsFactory = mock(GameObjectsFactoryA::class.java)
        val enemy: AbsEnemy = mock(EnemyA::class.java)
        `when`(goFactory.createEnemy(position = Position(0, 0))).thenReturn(enemy)
        val generateStrategy = BlockOfEnemiesStrategy()
        val enemies = generateStrategy.generateEnemies(goFactory);
        Assert.assertEquals(36, enemies.size)
    }
}
