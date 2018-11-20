class Value(val test: String)

data class User(val name: String, val age: Int)

// Properties can be declared in the class body
data class Person(val name: String) {
    var age: Int = 0
}

// Copying
val jack = User(name = "Jack", age = 1)
val olderJack = jack.copy(age = 2)

// Destructuring declarations
// Only allowed for local variables

fun createUser() {
    val jane = User("Jane", 35)
    val (name, age) = jane
    println("$name, $age years of age")
}

val pair = Pair(1,2)

