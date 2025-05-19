package model.type

import validation.validate
import validation.validator.PasswordValidator

@JvmInline
value class Password(val value: String) {
    init {
        validate { this@Password must PasswordValidator() }
    }
}
