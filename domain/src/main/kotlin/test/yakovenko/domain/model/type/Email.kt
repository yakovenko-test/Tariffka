package test.yakovenko.domain.model.type

import test.yakovenko.domain.validation.validate
import test.yakovenko.domain.validation.validator.EmailValidator

@JvmInline
value class Email(val value: String) {
    init {
        validate { this@Email must EmailValidator() }
    }
}
