package code.yakovenko.tariffka.common

interface Mapper<in From, out To> {
    fun transform(from: From): To
}

fun <From, To> Mapper<From, To>.transformAll(data: Collection<From>): Collection<To> {
    return data.map { transform(it) }
}
