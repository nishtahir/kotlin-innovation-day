// Declare the generic type parameter before the function name for it to be available in the receiver type expression
val <T> List<T>.lastIndex: Int
    get() = size - 1 // initializers are not allowed for extension properties


fun Any?.toString(): String {
    if (this == null) return "null"
    // after the null check, 'this' is autocast to a non-null type, so the toString() below
    // resolves to the member function of the Any class
    return toString()
}

class MyClass {
    companion object {
    }  // will be called "Companion"
}
​
fun MyClass.Companion.foo() {

}

class D
class C {
    fun D.foo() {
        toString()         // calls D.toString()
        this@C.toString()  // calls C.toString()
    }
}

