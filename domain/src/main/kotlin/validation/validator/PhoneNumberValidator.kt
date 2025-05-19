package validation.validator

import Regexes
import model.type.PhoneNumber
import validation.ValidationResult

class PhoneNumberValidator : Validator<PhoneNumber> {
    override operator fun invoke(target: PhoneNumber): ValidationResult {
        return if (Regexes.PHONE_NUMBER.matches(target.value)) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid("Invalid PhoneNumber format")
        }
    }
}
