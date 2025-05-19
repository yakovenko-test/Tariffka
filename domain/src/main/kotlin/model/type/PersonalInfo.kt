package model.type

import validation.validate
import validation.validator.StringFieldValidator

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
