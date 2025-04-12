package code.yakovenko.tariffka.core.extension

fun <E> Collection<E>.notContains(element: E): Boolean = !contains(element)
