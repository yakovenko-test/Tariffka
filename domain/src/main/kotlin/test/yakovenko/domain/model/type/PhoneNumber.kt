package test.yakovenko.domain.model.type

import test.yakovenko.domain.validation.validate
import test.yakovenko.domain.validation.validator.PhoneNumberValidator

@JvmInline
value class PhoneNumber(val value: String) {
    init {
        validate { this@PhoneNumber must PhoneNumberValidator() }
    }
}
