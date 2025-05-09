package code.yakovenko.core.validation

import code.yakovenko.core.extension.now
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

class LocalDateTimeValidator(
    private val timeZone: TimeZone
) : Validator<LocalDateTime> {
    override val errorMessages: List<String>
        get() = _errorMessages.toList()

    private val _errorMessages = mutableListOf<String>()

    override operator fun invoke(target: LocalDateTime, fieldName: String): Boolean {
        _errorMessages.clear()

        if (target > LocalDateTime.now(timeZone)) {
            _errorMessages.add("$fieldName must not be in the future")
        }

        return _errorMessages.isEmpty()
    }
}
