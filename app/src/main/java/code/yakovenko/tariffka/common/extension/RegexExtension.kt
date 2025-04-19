package code.yakovenko.tariffka.common.extension

fun Regex.notMatches(input: CharSequence) = !matches(input)
