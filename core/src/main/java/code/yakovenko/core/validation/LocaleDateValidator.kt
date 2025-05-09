package code.yakovenko.core.validation

import code.yakovenko.core.extension.now
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone

class LocaleDateValidator(
    private val timeZone: TimeZone
) : Validator<LocalDate> {
    override val errorMessages: List<String>
        get() = _errorMessages.toList()

    private val _errorMessages = mutableListOf<String>()

    override operator fun invoke(target: LocalDate, fieldName: String): Boolean {
        _errorMessages.clear()

        if (target > LocalDate.now(timeZone)) {
            _errorMessages.add("$fieldName must not be in the future")
        }

        return _errorMessages.isEmpty()
    }
}
