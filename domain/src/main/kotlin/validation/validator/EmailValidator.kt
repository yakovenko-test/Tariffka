package validation.validator

import Regexes
import model.type.Email
import validation.ValidationResult

class EmailValidator : Validator<Email> {
    override operator fun invoke(target: Email): ValidationResult {
        return if (Regexes.EMAIL_ADDRESS.matches(target.value)) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid("Invalid Email format")
        }
    }
}
