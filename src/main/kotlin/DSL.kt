import java.text.SimpleDateFormat
import java.util.Date

@DslMarker
annotation class ResidentDsl

data class Resident(val name: String,
    val dateOfBirth: Date,
    val addresses: List<Address>)

data class Address(var street: String? = null,
    var number: Int? = null,
    var city: String? = null)

//fun resident(block: (Resident) -> Unit): Resident {
//    val resident = Resident()
//    block(resident)
//    return resident
//}

//val newResident = resident {
//    it.name = "John"
//    it.age = 25
//}

fun resident(block: ResidentBuilder.() -> Unit): Resident = ResidentBuilder().apply(block).build()

@ResidentDsl
class ResidentBuilder {

    var name: String = ""

    private var dob: Date = Date()
    var dateOfBirth: String = ""
        set(value) {
            dob = SimpleDateFormat("yyyy-MM-dd").parse(value)
        }

    private var address: Address? = null
    private val addresses = mutableListOf<Address>()

    fun address(block: AddressBuilder.() -> Unit) {
        addresses.add(AddressBuilder().apply(block).build())
    }

    fun build(): Resident = Resident(name, dob, addresses)
}

@ResidentDsl
class AddressBuilder {
    var street: String = ""
    var number: Int = 0
    var city: String = ""
    fun build(): Address = Address(street, number, city)
}

val anotherResident = resident {
    name = "John"
    dateOfBirth = "1980-12-01"
    address {
        street = "Main Street"
        number = 12
        city = "London"
    }
    address {
        street = "Dev Avenue"
        number = 42
        city = "Paris"
    }
}