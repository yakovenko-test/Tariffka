package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import code.yakovenko.tariffka.MOCKED_OPERATOR

@Entity(tableName = "operators")
data class OperatorEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val url: String,
    val description: String,
    val yearOfFoundation: Int,
    val averageRating: Double,
)

fun OperatorEntity.toDomain() = MOCKED_OPERATOR
