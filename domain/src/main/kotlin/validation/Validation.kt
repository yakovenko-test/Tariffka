package validation

fun validate(block: ValidationScope.() -> Unit) {
    ValidationScope().apply(block).verify()
}
