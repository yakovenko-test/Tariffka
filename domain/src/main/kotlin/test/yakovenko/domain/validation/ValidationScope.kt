package test.yakovenko.domain.validation

import test.yakovenko.domain.validation.exception.ValidationException
import test.yakovenko.domain.validation.validator.NullableValidator
import test.yakovenko.domain.validation.validator.Validator

class ValidationScope {
    private val errors = mutableListOf<String>()

    infix fun <T> T?.must(validator: Validator<T>) {
        val validationResult = if (this == null) {
            NullableValidator(validator)(this)
        } else {
            validator(this)
        }

        if (validationResult is ValidationResult.Invalid) {
            errors.addAll(validationResult.errors)
        }
    }

    fun verify() {
        if (errors.isNotEmpty()) throw ValidationException(errors.joinToString())
    }
}
