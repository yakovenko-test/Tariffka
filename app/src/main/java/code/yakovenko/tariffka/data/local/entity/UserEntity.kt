package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import code.yakovenko.tariffka.core.utils.UserGender
import code.yakovenko.tariffka.core.utils.UserRole
import java.time.LocalDate

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val operatorId: Int?,
    val tariffId: Int?,
    val name: String,
    val surname: String,
    val patronymic: String?,
    val login: String,
    val phoneNumber: String,
    val email: String,
    val gender: UserGender,
    val role: UserRole,
    val birthDate: LocalDate,
    val password: String,
)
