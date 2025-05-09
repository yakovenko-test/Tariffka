package code.yakovenko.core.validation

class PasswordValidator(
    private val stringValidator: Validator<String>
) : Validator<String> {
    override val errorMessages: List<String>
        get() = _errorMessages.toList()

    private val _errorMessages = mutableListOf<String>()

    override operator fun invoke(target: String, fieldName: String): Boolean {
        _errorMessages.clear()

        if (!stringValidator(target, fieldName)) {
            _errorMessages.addAll(stringValidator.errorMessages)
        }

        lengthValidate(target, fieldName)
        lowerCaseValidate(target, fieldName)
        upperCaseValidate(target, fieldName)
        digitValidate(target, fieldName)

        return _errorMessages.isEmpty()
    }

    private fun lengthValidate(target: String, fieldName: String) {
        if (target.length < MIN_PASSWORD_LENGTH) {
            _errorMessages.add("$fieldName must contain at least $MIN_PASSWORD_LENGTH characters")
        }
    }

    private fun lowerCaseValidate(target: String, fieldName: String) {
        if (target.all { !it.isLowerCase() }) {
            _errorMessages.add("$fieldName must contain at least one lowercase letter (a-z)")
        }
    }

    private fun upperCaseValidate(target: String, fieldName: String) {
        if (target.all { !it.isUpperCase() }) {
            _errorMessages.add("$fieldName must contain at least one uppercase letter (A-Z)")
        }
    }

    private fun digitValidate(target: String, fieldName: String) {
        if (target.all { !it.isDigit() }) {
            _errorMessages.add("$fieldName must contain at least one digit (0-9)")
        }
    }

    companion object {
        private const val MIN_PASSWORD_LENGTH = 8
    }
}
