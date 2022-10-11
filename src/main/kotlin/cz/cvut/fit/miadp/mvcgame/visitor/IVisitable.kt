package cz.cvut.fit.miadp.mvcgame.visitor

interface IVisitable {
    fun accept(visitor: IVisitor): Unit
}