package code.yakovenko.tariffka.core.validation

import code.yakovenko.tariffka.core.Regexes
import code.yakovenko.tariffka.core.extension.notMatches

object PhoneNumberValidator : Validator<String> {
    override val errorMessages: List<String>
        get() = _errorMessages.toList()

    private val _errorMessages = mutableListOf<String>()

    override operator fun invoke(phoneNumber: String, fieldName: String): Boolean {
        _errorMessages.clear()

        if (!StringFieldValidator(phoneNumber, fieldName)) {
            _errorMessages.addAll(StringFieldValidator.errorMessages)
        }

        if (Regexes.PHONE_NUMBER.notMatches(phoneNumber)) {
            _errorMessages.add("Invalid $fieldName")
        }

        return _errorMessages.isEmpty()
    }
}
