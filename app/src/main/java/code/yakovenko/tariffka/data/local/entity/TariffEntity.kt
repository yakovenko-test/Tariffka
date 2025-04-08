package code.yakovenko.tariffka.data.local.entity

import androidx.room.ColumnInfo
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
            childColumns = ["operator_id"],
        )
    ],
    indices = [Index("operator_id")]
)
data class TariffEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("operator_id")
    val operatorId: Int,
    val name: String,
    val cost: Int,
    @ColumnInfo("minutes_count")
    val minutesCount: Int,
    @ColumnInfo("gigabytes_count")
    val gigabytesCount: Int,
    @ColumnInfo("average_rating")
    val averageRating: Double,
)
