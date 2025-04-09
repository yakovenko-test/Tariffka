package code.yakovenko.tariffka.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "discounts",
    foreignKeys = [
        ForeignKey(
            entity = TariffEntity::class,
            parentColumns = ["id"],
            childColumns = ["operator_id"],
        )
    ],
    indices = [Index("operator_id")]
)
data class DiscountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("operator_id")
    val operatorId: Int,
    val name: String,
    val description: String,
    @ColumnInfo("active_from")
    val activeFrom: LocalDateTime,
    @ColumnInfo("active_until")
    val activeUntil: LocalDateTime,
)
