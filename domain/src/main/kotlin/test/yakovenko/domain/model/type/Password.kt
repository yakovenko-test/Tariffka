package test.yakovenko.domain.model.type

import test.yakovenko.domain.validation.validate
import test.yakovenko.domain.validation.validator.PasswordValidator

@JvmInline
value class Password(val value: String) {
    init {
        validate { this@Password must PasswordValidator() }
    }
}
