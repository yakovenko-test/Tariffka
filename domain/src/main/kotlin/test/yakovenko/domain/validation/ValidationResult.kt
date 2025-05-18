package test.yakovenko.domain.validation

sealed class ValidationResult {
    data object Valid : ValidationResult()

    data class Invalid(val errors: List<String>) : ValidationResult() {
        constructor(vararg errors: String) : this(errors.toList())
    }
}
