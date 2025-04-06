package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "tariff_discounts",
    foreignKeys = [
        ForeignKey(
            entity = TariffEntity::class,
            parentColumns = ["id"],
            childColumns = ["tariffId"],
        )
    ],
    indices = [Index("tariffId")]
)
data class TariffDiscountEntity(
    @PrimaryKey val id: Int,
    val tariffId: Int,
    val newCost: Int,
    val activeFrom: LocalDateTime,
    val activeUntil: LocalDateTime,
)
