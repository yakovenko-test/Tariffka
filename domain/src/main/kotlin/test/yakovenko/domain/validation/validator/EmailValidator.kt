package test.yakovenko.domain.validation.validator

import test.yakovenko.common.Regexes
import test.yakovenko.domain.model.type.Email
import test.yakovenko.domain.validation.ValidationResult

class EmailValidator : Validator<Email> {
    override operator fun invoke(target: Email): ValidationResult {
        return if (Regexes.EMAIL_ADDRESS.matches(target.value)) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid("Invalid Email format")
        }
    }
}
