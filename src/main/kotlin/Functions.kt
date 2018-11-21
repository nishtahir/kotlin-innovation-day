// Local functions

fun outer() {

    val language = "Kotlin"

    fun inner() = "$language is the best"

    fun anotherInner() {
    }

    val result = inner()
    anotherInner()
}



// Infix functions
// "to" is an infix function
val myMap = mapOf(
    1 to "one",
    2 to "two",
    3 to "three"
)

class Assertion<T>(private val target: T) {
    infix fun isEqualTo(other: T) = other == target

    infix fun isDifferentFrom(other: T) = other != target
}

val result = Assertion(5)

result isEqualTo 5 // This passes
result isEqualTo 6 // This fails the assertion
result isDifferentFrom 5 // This also fails the assertion