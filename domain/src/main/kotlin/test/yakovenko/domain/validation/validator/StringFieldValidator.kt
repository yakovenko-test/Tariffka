package test.yakovenko.domain.validation.validator

import test.yakovenko.domain.validation.ValidationResult

class StringFieldValidator(
    private val fieldName: String
) : Validator<String> {
    override operator fun invoke(target: String): ValidationResult {
        return when {
            target.isEmpty() -> {
                ValidationResult.Invalid("$fieldName must not be empty")
            }

            target.first().isWhitespace() || target.last().isWhitespace() -> {
                ValidationResult.Invalid(
                    "$fieldName must not start or end with a whitespace character"
                )
            }

            else -> ValidationResult.Valid
        }
    }
}
