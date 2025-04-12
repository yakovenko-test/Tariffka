package code.yakovenko.tariffka.core.validation

object EstimationValidator : Validator<UByte> {
    override val errorMessages: List<String>
        get() = _errorMessages

    private val _errorMessages = mutableListOf<String>()

    private const val MIN_ESTIMATION = 0
    private const val MAX_ESTIMATION = 5

    override fun invoke(estimation: UByte, fieldName: String): Boolean {
        _errorMessages.clear()

        if (estimation !in MIN_ESTIMATION.toUByte()..MAX_ESTIMATION.toUByte()) {
            _errorMessages.add("$fieldName must be in range [$MIN_ESTIMATION; $MAX_ESTIMATION]")
        }

        return _errorMessages.isEmpty()
    }

    operator fun invoke(averageEstimate: Double, fieldName: String): Boolean {
        _errorMessages.clear()

        if (averageEstimate !in MIN_ESTIMATION.toDouble()..MAX_ESTIMATION.toDouble()) {
            _errorMessages.add("$fieldName must be in range [$MIN_ESTIMATION.0; $MAX_ESTIMATION.0]")
        }

        return _errorMessages.isEmpty()
    }
}
