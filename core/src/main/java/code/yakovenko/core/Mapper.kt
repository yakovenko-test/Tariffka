package code.yakovenko.core

interface Mapper<in From, out To> {
    fun transform(from: From): To
}
