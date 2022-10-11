package cz.cvut.fit.miadp.mvcgame.command

import cz.cvut.fit.miadp.mvcgame.memento.Caretaker
import cz.cvut.fit.miadp.mvcgame.model.IGameModel

abstract class AbstractGameCommand(private val subject: IGameModel) {

    lateinit var memento: Any

    protected abstract fun execute()

    fun doExecute(){
        memento = subject.createMemento()
        execute()
    }

    fun unExecute(){
        subject.setMemento(memento)
    }
}