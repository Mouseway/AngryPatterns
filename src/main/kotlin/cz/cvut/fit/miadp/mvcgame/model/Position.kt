package cz.cvut.fit.miadp.mvcgame.model

data class Position(var x: Int = 0, var y: Int = 0) {
    fun add(v: Vector) {
        x += v.x
        y += v.y
    }
}