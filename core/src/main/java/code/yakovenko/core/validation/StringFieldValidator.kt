package code.yakovenko.core.validation

class StringFieldValidator : Validator<String> {
    override val errorMessages: List<String>
        get() = _errorMessages

    private val _errorMessages = mutableListOf<String>()

    override operator fun invoke(target: String, fieldName: String): Boolean {
        _errorMessages.clear()

        if (target.isEmpty()) {
            _errorMessages.add("$fieldName must not be empty")
        } else if (target.first().isWhitespace() || target.last().isWhitespace()) {
            _errorMessages.add("$fieldName must not start or end with a whitespace character")
        }

        return _errorMessages.isEmpty()
    }
}
