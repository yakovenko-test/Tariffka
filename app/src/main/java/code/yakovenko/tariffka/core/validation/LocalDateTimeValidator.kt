package code.yakovenko.tariffka.core.validation

import java.time.LocalDateTime

object LocalDateTimeValidator : Validator<LocalDateTime> {
    override val errorMessages: List<String>
        get() = _errorMessages.toList()

    private val _errorMessages = mutableListOf<String>()

    override operator fun invoke(localDateTime: LocalDateTime, fieldName: String): Boolean {
        _errorMessages.clear()

        if (localDateTime > LocalDateTime.now()) {
            _errorMessages.add("$fieldName must not be in the future")
        }

        return _errorMessages.isEmpty()
    }

    operator fun invoke(
        leftLocalDateTime: LocalDateTime,
        rightLocalDateTime: LocalDateTime,
        leftFieldName: String,
        rightFieldName: String
    ): Boolean {
        _errorMessages.clear()

        if (leftLocalDateTime > rightLocalDateTime) {
            _errorMessages.add("$leftFieldName must be less or equal than $rightFieldName")
        }

        return _errorMessages.isEmpty()
    }
}
