package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.core.enums.UserGender
import code.yakovenko.tariffka.core.enums.UserRole
import code.yakovenko.tariffka.core.validation.EmailValidator
import code.yakovenko.tariffka.core.validation.LocaleDateValidator
import code.yakovenko.tariffka.core.validation.PasswordValidator
import code.yakovenko.tariffka.core.validation.PhoneNumberValidator
import code.yakovenko.tariffka.core.validation.StringFieldValidator
import code.yakovenko.tariffka.core.validation.UsernameValidator
import java.time.LocalDate
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class User @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid = Uuid.random(),
    val operatorId: Uuid?,
    val tariffId: Uuid?,
    val name: String,
    val surname: String,
    val patronymic: String?,
    val username: String,
    val phoneNumber: String,
    val email: String?,
    val gender: UserGender?,
    val birthDate: LocalDate?,
    val password: String,
    val role: UserRole,
) {
    init {
        require(StringFieldValidator(name, "Name")) {
            StringFieldValidator.errorMessages.joinToString()
        }
        require(StringFieldValidator(surname, "Surname")) {
            StringFieldValidator.errorMessages.joinToString()
        }
        require(patronymic?.let { StringFieldValidator(it, "Patronymic") } != false) {
            StringFieldValidator.errorMessages.joinToString()
        }
        require(UsernameValidator(username, "Username")) {
            UsernameValidator.errorMessages.joinToString()
        }
        require(PhoneNumberValidator(phoneNumber, "PhoneNumber")) {
            PhoneNumberValidator.errorMessages.joinToString()
        }
        require(email?.let { EmailValidator(it, "Email") } != false) {
            EmailValidator.errorMessages.joinToString()
        }
        require(birthDate?.let { LocaleDateValidator(it, "BirthDate") } != false) {
            LocaleDateValidator.errorMessages.joinToString()
        }
        require(PasswordValidator(password, "Password")) {
            PasswordValidator.errorMessages.joinToString()
        }
    }
}
