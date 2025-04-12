package code.yakovenko.tariffka.core.validation

import java.time.Year

object YearValidator : Validator<UInt> {
    override val errorMessages: List<String>
        get() = _errorMessages.toList()

    private val _errorMessages = mutableListOf<String>()

    override operator fun invoke(year: UInt, fieldName: String): Boolean {
        _errorMessages.clear()

        if (year > Year.now().value.toUInt()) {
            _errorMessages.add("$fieldName must not be in the future")
        }

        return _errorMessages.isEmpty()
    }
}
