package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import code.yakovenko.tariffka.MOCKED_OPTION

@Entity(
    tableName = "options",
    foreignKeys = [
        ForeignKey(
            entity = OperatorEntity::class,
            parentColumns = ["id"],
            childColumns = ["operatorId"]
        )
    ]
)
data class OptionEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val cost: Int,
    val description: String,
    val operatorId: Long,
)

fun OptionEntity.toDomain() = MOCKED_OPTION
