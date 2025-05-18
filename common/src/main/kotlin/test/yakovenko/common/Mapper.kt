package test.yakovenko.common

interface Mapper<in From, out To> {
    fun transform(from: From): To
}

fun <From, To> Mapper<From, To>.transformAll(from: Collection<From>): Collection<To> {
    return from.map { transform(it) }
}
