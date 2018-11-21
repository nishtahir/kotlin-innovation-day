interface DataProcessor<T> {
    fun processData(): T
}

class NoResultDataProcessor : DataProcessor<Unit> { // Use "no value" as a type argument
    override fun processData() {
        // No need of a explicit return
    }
}

fun reportError(message: String): Nothing {
    throw RuntimeException(message)
}

var i = 0

fun exampleOne() {
    reportError("Error") // throws RuntimeException
    i = 1         // 'Unreachable code' warning here
}

fun exampleTwo(map: Map<String, String>) {
    val data: String = map["key"] ?: reportError("Value for key does not exist") // Compiles! Note the type is String.
}

fun exampleThree(n: Int): String {
    if (n > 5) {
        return "Ok"
    }
    reportError("Error: n <= 5") //  Compiles! Throws RuntimeException

}