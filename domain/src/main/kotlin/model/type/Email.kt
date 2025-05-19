package model.type

import validation.validate
import validation.validator.EmailValidator

@JvmInline
value class Email(val value: String) {
    init {
        validate { this@Email must EmailValidator() }
    }
}
