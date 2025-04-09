package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.core.utils.UserGender
import code.yakovenko.tariffka.core.utils.UserRole
import java.time.LocalDate

data class User(
    val id: Int,
    val operatorId: Int?,
    val tariffId: Int?,
    val name: String,
    val surname: String,
    val patronymic: String?,
    val login: String,
    val phoneNumber: String,
    val email: String?,
    val gender: UserGender?,
    val role: UserRole,
    val birthDate: LocalDate?,
    val password: String,
) {
    init {
        require(name.isNotBlank())
        require(surname.isNotBlank())
        require(patronymic?.isNotBlank() == true)
        require(login.isNotBlank())
        require(birthDate?.let { it <= LocalDate.now() } != false)
        require(password.isNotBlank())
    }
}
