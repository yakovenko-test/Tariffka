package code.yakovenko.tariffka.common.validation

import code.yakovenko.tariffka.common.Regexes
import code.yakovenko.tariffka.common.extension.notMatches

object WebURLValidator : Validator<String> {
    override val errorMessages: List<String>
        get() = _errorMessages

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
