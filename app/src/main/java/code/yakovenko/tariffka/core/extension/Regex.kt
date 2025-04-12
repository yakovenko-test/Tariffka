package code.yakovenko.tariffka.core.extension

fun Regex.notMatches(input: CharSequence) = !matches(input)
