package code.yakovenko.tariffka.common.validation

import code.yakovenko.tariffka.common.extension.now
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone

object LocaleDateValidator : Validator<LocalDate> {
    override val errorMessages: List<String>
        get() = _errorMessages

    private val _errorMessages = mutableListOf<String>()

    private val timeZone = TimeZone.currentSystemDefault()

    override operator fun invoke(localDate: LocalDate, fieldName: String): Boolean {
        _errorMessages.clear()

        if (localDate > LocalDate.now(timeZone)) {
            _errorMessages.add("$fieldName must not be in the future")
        }

        return _errorMessages.isEmpty()
    }
}
