package code.yakovenko.tariffka.common.validation

import code.yakovenko.tariffka.common.Regexes
import code.yakovenko.tariffka.common.extension.notMatches

object PhoneNumberValidator : Validator<String> {
    override val errorMessages: List<String>
        get() = _errorMessages

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
