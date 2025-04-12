package code.yakovenko.tariffka.core.validation

import java.time.LocalDate

object LocaleDateValidator : Validator<LocalDate> {
    override val errorMessages: List<String>
        get() = _errorMessages.toList()

    private val _errorMessages = mutableListOf<String>()

    override operator fun invoke(localDate: LocalDate, fieldName: String): Boolean {
        _errorMessages.clear()

        if (localDate > LocalDate.now()) {
            _errorMessages.add("$fieldName must not be in the future")
        }

        return _errorMessages.isEmpty()
    }
}
