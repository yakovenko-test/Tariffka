package code.yakovenko.tariffka.data.local.entity

import androidx.room.ColumnInfo
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
            childColumns = ["tariff_id"],
        )
    ],
    indices = [Index("tariff_id")]
)
data class TariffDiscountEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("tariff_id")
    val tariffId: Int,
    @ColumnInfo("new_cost")
    val newCost: Int,
    @ColumnInfo("active_from")
    val activeFrom: LocalDateTime,
    @ColumnInfo("active_until")
    val activeUntil: LocalDateTime,
)
