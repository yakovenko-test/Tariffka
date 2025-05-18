package test.yakovenko.domain.validation

fun validate(block: ValidationScope.() -> Unit) {
    ValidationScope().apply(block).verify()
}
