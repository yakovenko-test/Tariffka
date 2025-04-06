package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tariffs",
    foreignKeys = [
        ForeignKey(
            entity = OperatorEntity::class,
            parentColumns = ["id"],
            childColumns = ["operatorId"],
        )
    ],
    indices = [Index("operatorId")]
)
data class TariffEntity(
    @PrimaryKey val id: Int,
    val operatorId: Int,
    val name: String,
    val cost: Int,
    val minutesCount: Int,
    val gigabytesCount: Int,
    val averageRating: Double,
)
