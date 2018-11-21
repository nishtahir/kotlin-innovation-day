data class Point(val x: Int, val y: Int) {

    companion object {

        fun newInstance() {

        }

        operator fun invoke() : Point {
            return Point(0, 0)
        }

    }

    operator fun plus(point: Point): Point = Point( x = x + point.x, y = y + point.y)

    operator fun invoke() {

    }
}

operator fun Point.unaryMinus() = Point(-x, -y)

val point = Point(10, 20)

fun main() {
    println(-point)  // prints "Point(x=-10, y=-20)"

    point() // Is an operator, not constructor or function


    Point.newInstance()

    Point()
}

// Overloaded operators for collections
fun listItemsToDisplay(): List<String> {
    // public operator fun <T> Collection<T>.plus(elements: Iterable<T>): List<T>
    return listOf("one") + listOf("two") + listOf("three")
}
