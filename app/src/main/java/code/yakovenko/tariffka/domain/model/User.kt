package code.yakovenko.tariffka.domain.model

import android.util.Patterns
import code.yakovenko.tariffka.data.local.entity.UserEntity
import code.yakovenko.tariffka.domain.model.utils.UserGender
import code.yakovenko.tariffka.domain.model.utils.UserRole
import java.util.Date

data class User(
    val id: IdType,
    val name: String,
    val surname: String,
    val patronymic: String?,
    val email: String,
    val phoneNumber: String,
    val login: String,
    val password: String,
    val birthDate: Date,
    val gender: UserGender,
    val role: UserRole,
    val operator: Operator?,
    val tariff: Tariff?,
) {
    init {
        require(Patterns.EMAIL_ADDRESS.matcher(email).matches())
        require(Patterns.PHONE.matcher(phoneNumber).matches())
        require(birthDate <= Date())
    }
}

fun User.toData() = UserEntity(id.id)
