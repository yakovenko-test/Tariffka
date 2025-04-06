package code.yakovenko.tariffka.data.local.entity

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
            childColumns = ["operatorId"]
        )
    ],
    indices = [Index("operatorId")]
)
data class ServiceEntity(
    @PrimaryKey val id: Int,
    val operatorId: Int,
    val name: String,
    val cost: Int,
    val description: String,
)

