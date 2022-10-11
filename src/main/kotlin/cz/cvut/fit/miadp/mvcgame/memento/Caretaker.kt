package cz.cvut.fit.miadp.mvcgame.memento

import cz.cvut.fit.miadp.mvcgame.model.IGameModel

class Caretaker {
    private lateinit var model: IGameModel
    private val mementos: MutableList<Any> = ArrayList()

    fun setModel(model: IGameModel) {
        this.model = model
    }

    fun createMemento(): Any {
        return run {
            val memento = model.createMemento()
            mementos.add(memento)
            memento
        }
    }

    fun setMemento() {
        if (mementos.size > 0) {
            model!!.setMemento(mementos[mementos.size - 1])
        }
    }

    companion object{
        private object SingletonHolder {
            val INSTANCE = Caretaker()
        }
        fun getInstance(): Caretaker {
            return SingletonHolder.INSTANCE
        }
    }
}