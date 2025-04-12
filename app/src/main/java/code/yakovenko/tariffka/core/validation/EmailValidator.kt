package code.yakovenko.tariffka.core.validation

import code.yakovenko.tariffka.core.Regexes
import code.yakovenko.tariffka.core.extension.notMatches

object EmailValidator : Validator<String> {
    override val errorMessages: List<String>
        get() = _errorMessages.toList()

    private val _errorMessages = mutableListOf<String>()

    override operator fun invoke(email: String, fieldName: String): Boolean {
        _errorMessages.clear()

        if (!StringFieldValidator(email, fieldName)) {
            _errorMessages.addAll(StringFieldValidator.errorMessages)
        }

        if (Regexes.EMAIL_ADDRESS.notMatches(email)) {
            _errorMessages.add("$fieldName email")
        }

        return _errorMessages.isEmpty()
    }
}
