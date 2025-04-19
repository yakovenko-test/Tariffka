package code.yakovenko.tariffka.common.validation

import code.yakovenko.tariffka.common.exception.ValidationException
import kotlinx.datetime.LocalDateTime

fun <T> validate(value: T?, fieldName: String, validator: Validator<T>) {
    value?.let {
        if (validator(value, fieldName)) {
            throw ValidationException(validator.errorMessages.joinToString())
        }
    }
}

fun validateLocaleDateTime(
    activeFrom: LocalDateTime,
    activeUntil: LocalDateTime?
) {
    activeUntil?.let {
        require(LocalDateTimeValidator(activeFrom, activeUntil, "ActiveFrom", "ActiveUntil")) {
            LocalDateTimeValidator.errorMessages.joinToString()
        }
    }
}