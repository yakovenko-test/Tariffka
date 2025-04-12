package code.yakovenko.tariffka.core.validation

import code.yakovenko.tariffka.core.Regexes
import code.yakovenko.tariffka.core.extension.notMatches

object URLValidator : Validator<String> {
    override val errorMessages: List<String>
        get() = _errorMessages.toList()

    private val _errorMessages = mutableListOf<String>()

    override operator fun invoke(url: String, fieldName: String): Boolean {
        _errorMessages.clear()

        if (!StringFieldValidator(url, fieldName)) {
            _errorMessages.addAll(StringFieldValidator.errorMessages)
        }

        if (Regexes.WEB_URL.notMatches(url)) {
            _errorMessages.add("Invalid $fieldName")
        }

        return _errorMessages.isEmpty()
    }
}
