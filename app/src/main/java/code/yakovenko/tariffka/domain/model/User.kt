package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.domain.type.UserGender
import code.yakovenko.tariffka.domain.type.UserRole
import code.yakovenko.tariffka.common.validation.LocaleDateValidator
import code.yakovenko.tariffka.common.validation.PasswordValidator
import code.yakovenko.tariffka.common.validation.PhoneNumberValidator
import code.yakovenko.tariffka.common.validation.StringFieldValidator
import code.yakovenko.tariffka.common.validation.validate
import kotlinx.datetime.LocalDate
import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val operatorId: UUID?,
    val tariffId: UUID?,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val birthDate: LocalDate,
    val password: String,
    val role: UserRole,
) {
    init {
        validate(name, "Name", StringFieldValidator)
        validate(surname, "Surname", StringFieldValidator)
        validate(phoneNumber, "PhoneNumber", PhoneNumberValidator)
        validate(birthDate, "BirthDate", LocaleDateValidator)
        validate(password, "Password", PasswordValidator)
    }
}
