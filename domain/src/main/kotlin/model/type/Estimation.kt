package model.type

enum class Estimation(val value: UInt) {
    ZERO(0U),
    ONE(1U),
    TWO(2U),
    THREE(3U),
    FOUR(4U),
    FIVE(5U);

    companion object {
        val lowest = entries.first()
        val highest = entries.last()
    }
}
