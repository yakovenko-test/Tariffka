package code.yakovenko.tariffka.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import code.yakovenko.tariffka.core.utils.UserGender
import code.yakovenko.tariffka.core.utils.UserRole
import java.time.LocalDate

@Entity(
    tableName = "users",
    foreignKeys = [
        ForeignKey(
            entity = OperatorEntity::class,
            parentColumns = ["id"],
            childColumns = ["operator_id"],
        ),
        ForeignKey(
            entity = TariffEntity::class,
            parentColumns = ["id"],
            childColumns = ["tariff_id"],
        )
    ],
    indices = [Index("operator_id"), Index("tariff_id")]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("operator_id")
    val operatorId: Int?,
    @ColumnInfo("tariff_id")
    val tariffId: Int?,
    val name: String,
    val surname: String,
    val patronymic: String?,
    val login: String,
    @ColumnInfo("phone_number")
    val phoneNumber: String,
    val email: String?,
    val gender: UserGender?,
    val role: UserRole,
    @ColumnInfo("birth_date")
    val birthDate: LocalDate?,
    val password: String,
)
