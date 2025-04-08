package code.yakovenko.tariffka.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "services",
    foreignKeys = [
        ForeignKey(
            entity = OperatorEntity::class,
            parentColumns = ["id"],
            childColumns = ["operator_id"]
        )
    ],
    indices = [Index("operator_id")]
)
data class ServiceEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("operator_id")
    val operatorId: Int,
    val name: String,
    val cost: Int,
    val description: String?,
)
