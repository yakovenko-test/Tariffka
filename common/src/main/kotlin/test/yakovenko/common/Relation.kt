package test.yakovenko.common

enum class Relation(val description: String) {
    GT("greater than") {
        override fun <T: Comparable<T>> check(first: T, second: T) = first > second
    },
    LT("less than") {
        override fun <T: Comparable<T>> check(first: T, second: T) = first < second
    },
    LE("less than or equal") {
        override fun <T: Comparable<T>> check(first: T, second: T) = first <= second
    };

    abstract fun <T: Comparable<T>> check(first: T, second: T): Boolean
}
