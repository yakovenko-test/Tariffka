package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.domain.model.utils.UserGender
import code.yakovenko.tariffka.domain.model.utils.UserRole
import java.util.Date

data class User(
    val id: Long,
    val name: String,
    val surname: String,
    val patronymic: String?,
    val email: String,
    val phoneNumber: String,
    val login: String,
    var password: String,
    val birthDate: Date,
    val gender: UserGender,
    val role: UserRole,
    var operator: Operator?,
    var tariff: Tariff?,
    val options: MutableList<Option>,
)
