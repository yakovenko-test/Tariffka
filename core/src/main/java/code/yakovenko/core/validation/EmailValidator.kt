package code.yakovenko.core.validation

import code.yakovenko.core.Regexes

class EmailValidator(
    private val stringValidator: Validator<String>
) : Validator<String> {
    override val errorMessages: List<String>
        get() = _errorMessages

    private val _errorMessages = mutableListOf<String>()

    override operator fun invoke(target: String, fieldName: String): Boolean {
        _errorMessages.clear()

        if (!stringValidator(target, fieldName)) {
            _errorMessages.addAll(stringValidator.errorMessages)
        }

        if (!Regexes.EMAIL_ADDRESS.matches(target)) {
            _errorMessages.add("Invalid $fieldName")
        }

        return _errorMessages.isEmpty()
    }
}
