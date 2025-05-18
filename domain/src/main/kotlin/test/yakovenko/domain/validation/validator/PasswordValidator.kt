package test.yakovenko.domain.validation.validator

import test.yakovenko.domain.model.type.Password
import test.yakovenko.domain.validation.ValidationResult

class PasswordValidator : Validator<Password> {
    override operator fun invoke(target: Password): ValidationResult {
        val errors = mutableListOf<String>()

        if (target.value.length < MIN_PASSWORD_LENGTH) {
            errors.add("Password must contain at least $MIN_PASSWORD_LENGTH characters")
        }
        if (target.value.none { it.isLowerCase() }) {
            errors.add("Password must contain at least one lowercase letter (a-z)")
        }
        if (target.value.none { it.isUpperCase() }) {
            errors.add("Password must contain at least one uppercase letter (A-Z)")
        }
        if (target.value.none { it.isDigit() }) {
            errors.add("Password must contain at least one digit (0-9)")
        }

        return if (errors.isEmpty()) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid(errors)
        }
    }

    companion object {
        private const val MIN_PASSWORD_LENGTH = 8
    }
}
