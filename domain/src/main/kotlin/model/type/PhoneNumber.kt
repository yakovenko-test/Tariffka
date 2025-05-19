package model.type

import validation.validate
import validation.validator.PhoneNumberValidator

@JvmInline
value class PhoneNumber(val value: String) {
    init {
        validate { this@PhoneNumber must PhoneNumberValidator() }
    }
}
