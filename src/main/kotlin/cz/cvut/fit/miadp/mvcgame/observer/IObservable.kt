package cz.cvut.fit.miadp.mvcgame.observer

interface IObservable {
    fun registerObserver(obs: IObserver): Unit
    fun unregisterObserver(obs: IObserver): Unit
    fun notifyObservers(): Unit
}