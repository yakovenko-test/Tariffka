package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import code.yakovenko.tariffka.MOCKED_TARIFF

@Entity(
    tableName = "tariffs",
    foreignKeys = [
        ForeignKey(
            entity = OperatorEntity::class,
            parentColumns = ["id"],
            childColumns = ["operatorId"],
        )
    ]
)
data class TariffEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val cost: Int,
    val averageRating: Double,
    val minutesCount: Int,
    val gigabytesCount: Int,
    val operatorId: IdType,
)

fun TariffEntity.toDomain() = MOCKED_TARIFF
