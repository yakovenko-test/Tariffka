package validation

import validation.exception.ValidationException
import validation.validator.NullableValidator
import validation.validator.Validator

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
