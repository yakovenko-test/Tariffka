package code.yakovenko.tariffka.core.validation

object UsernameValidator : Validator<String> {
    override val errorMessages: List<String>
        get() = _errorMessages.toList()

    private val _errorMessages = mutableListOf<String>()

    private const val MIN_USERNAME_LENGTH = 3
    private const val MAX_USERNAME_LENGTH = 30

    override operator fun invoke(username: String, fieldName: String): Boolean {
        _errorMessages.clear()

        if (!StringFieldValidator(username, fieldName)) {
            _errorMessages.addAll(StringFieldValidator.errorMessages)
        }

        lengthValidate(username, fieldName)
        firstSymbolValidate(username, fieldName)

        return _errorMessages.isEmpty()
    }

    private fun lengthValidate(username: String, fieldName: String): Boolean {
        return (username.length < MIN_USERNAME_LENGTH ||
                username.length > MAX_USERNAME_LENGTH).takeIf { it }?.also {
            _errorMessages.add(
                "$fieldName length must must be in range " +
                        "[$MIN_USERNAME_LENGTH; $MAX_USERNAME_LENGTH]"
            )
        } != true
    }

    private fun firstSymbolValidate(username: String, fieldName: String): Boolean {
        return (!username.first().isLetter()).takeIf { it }?.also {
            _errorMessages.add("$fieldName must start with a letter")
        } != true
    }
}
