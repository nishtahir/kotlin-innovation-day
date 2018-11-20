enum class RGB { RED, GREEN, BLUE }
​
inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}


fun `Hey Enums!`() {
    ​printAllValues<RGB>() // prints RED, GREEN, BLUE
}




enum class Hand {
    ROCK {
        override val beats: Hand get() = SCISSORS
    },
    PAPER {
        override val beats: Hand get() = ROCK
    },
    SCISSORS {
        override val beats: Hand get() = PAPER
    };

    abstract val beats: Hand
}

