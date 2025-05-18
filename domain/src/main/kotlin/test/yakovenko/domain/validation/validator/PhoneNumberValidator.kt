package test.yakovenko.domain.validation.validator

import test.yakovenko.common.Regexes
import test.yakovenko.domain.model.type.PhoneNumber
import test.yakovenko.domain.validation.ValidationResult

class PhoneNumberValidator : Validator<PhoneNumber> {
    override operator fun invoke(target: PhoneNumber): ValidationResult {
        return if (Regexes.PHONE_NUMBER.matches(target.value)) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid("Invalid PhoneNumber format")
        }
    }
}
