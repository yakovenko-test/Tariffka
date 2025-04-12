package code.yakovenko.tariffka.core.validation

object PasswordValidator : Validator<String> {
    override val errorMessages: List<String>
        get() = _errorMessages.toList()

    private val _errorMessages = mutableListOf<String>()

    private const val MIN_PASSWORD_LENGTH = 8

    override operator fun invoke(password: String, fieldName: String): Boolean {
        _errorMessages.clear()

        if (!StringFieldValidator(password, fieldName)) {
            _errorMessages.addAll(StringFieldValidator.errorMessages)
        }

        lengthValidate(password, fieldName)
        lowerCaseValidate(password, fieldName)
        upperCaseValidate(password, fieldName)
        digitValidate(password, fieldName)

        return _errorMessages.isEmpty()
    }

    private fun lengthValidate(password: String, fieldName: String): Boolean {
        return (password.length < MIN_PASSWORD_LENGTH).takeIf { it }?.also {
            _errorMessages.add("$fieldName must contain at least $MIN_PASSWORD_LENGTH characters")
        } != true
    }

    private fun lowerCaseValidate(password: String, fieldName: String): Boolean {
        return (password.all { !it.isLowerCase() }).takeIf { it }?.also {
            _errorMessages.add("$fieldName must contain at least one lowercase letter (a-z)")
        } != true
    }

    private fun upperCaseValidate(password: String, fieldName: String): Boolean {
        return (password.all { !it.isUpperCase() }).takeIf { it }?.also {
            _errorMessages.add("$fieldName must contain at least one uppercase letter (A-Z)")
        } != true
    }

    private fun digitValidate(password: String, fieldName: String): Boolean {
        return (password.all { !it.isDigit() }).takeIf { it }?.also {
            _errorMessages.add("$fieldName must contain at least one digit (0-9)")
        } != true
    }
}
