package test.yakovenko.domain.model.type

import test.yakovenko.domain.validation.validate
import test.yakovenko.domain.validation.validator.StringFieldValidator

data class PersonalInfo(
    val name: String,
    val surname: String,
    val patronymic: String?,
) {
    init {
        validate {
            name must StringFieldValidator("Name")
            surname must StringFieldValidator("Surname")
            patronymic must StringFieldValidator("Patronymic")
        }
    }
}
