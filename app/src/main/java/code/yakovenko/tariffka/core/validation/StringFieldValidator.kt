package code.yakovenko.tariffka.core.validation


object StringFieldValidator : Validator<String> {
    override val errorMessages: List<String>
        get() = _errorMessages.toList()

    private val _errorMessages = mutableListOf<String>()

    override operator fun invoke(string: String, fieldName: String): Boolean {
        _errorMessages.clear()

        if (string.isEmpty()) {
            _errorMessages.add("$fieldName must not be empty")
        } else if (string.first().isWhitespace() || string.last().isWhitespace()) {
            _errorMessages.add("$fieldName must not start or end with a whitespace character")
        }

        return _errorMessages.isEmpty()
    }
}
