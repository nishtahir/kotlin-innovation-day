import kotlin.properties.Delegates.notNull
import kotlin.properties.Delegates.observable
import kotlin.properties.Delegates.vetoable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() {
        print(x)
    }
}

class Derived(b: Base) : Base by b

fun printImpl() {
    val b = BaseImpl(10)
    Derived(b).print()
}

// Delegated Properties

val lazyData: Double by lazy(LazyThreadSafetyMode.NONE) {
    println("Initializing lazyData")
    2.0
} //  performs its initializer where the defined property is first used, not its declaration.



fun example(computeFoo: () -> Int) {
    // Declare local variables as delegated properties (since 1.1)

    val memoizedFoo by lazy(computeFoo)
    ​
    if (someCondition && memoizedFoo.isValid()) {
        memoizedFoo.doSomething()
    }
}

class Student(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}

val student = Student(mapOf(
    "name" to "John Doe",
    "age"  to 25
))

println(student.name) // Prints "John Doe"
println(student.age)  // Prints 25

val age by notNull<Int>()
println(age) // Referencing before it’s set causes an IllegalStateException

data class DataHolder(var data: String)

// Observable: action must be done each time a property’s value changes
var observableData: DataHolder by observable(DataHolder("Initial value")) { property, oldValue, newValue ->
    println("${property.name}: $oldValue -> $newValue")
}

// Does not notify the delegate if an internal class property changed
observableData.data = “new value”

// Vetoable: a condition that must be met for the property’s value to be modified. Returns a boolean
var vetoableData: Int by vetoable(0) { property, oldValue, newValue ->
    println("${property.name}: $oldValue -> $newValue")
    newValue >= 0
}


// Create your own delegate - Example: SharedPreferences

fun SharedPreferences.stringDelegate(key: String, default: String) =
    delegate<String>(key, default, SharedPreferences::getString, SharedPreferences.Editor::putString)

fun SharedPreferences.booleanDelegate(key: String, default: Boolean) =
    delegate(key, default, SharedPreferences::getBoolean, SharedPreferences.Editor::putBoolean)

fun SharedPreferences.intDelegate(key: String, default: Int) =
    delegate(key, default, SharedPreferences::getInt, SharedPreferences.Editor::putInt)

inline fun <reified T : Enum<T>> SharedPreferences.enumDelegate(key: String, default: T) = delegate(key, default,
    { prefsKey, default ->
        this.getInt(prefsKey, -1).let { if (it >= 0) kotlin.enumValues()[it] else default }
    },
    { prefsKey, value -> this.putInt(prefsKey, value.ordinal) })

inline fun <T> SharedPreferences.delegate(
    key: String,
    defaultValue: T,
    crossinline getter: SharedPreferences.(String, T) -> T,
    crossinline setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor
): ReadWriteProperty<Any, T> {

    return object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>) =
            getter(key, defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) =
            edit().setter(key, value).apply()
    }
}



