data class Point(val x: Int, val y: Int)

operator fun Point.unaryMinus() = Point(-x, -y)

val point = Point(10, 20)

fun main() {
    println(-point)  // prints "Point(x=-10, y=-20)"
}

// Overloaded operators for collections
fun listItemsToDisplay(): List<String> {
    // public operator fun <T> Collection<T>.plus(elements: Iterable<T>): List<T>
    return listOf("one") + listOf("two") + listOf("three")
}
