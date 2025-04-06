package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operators")
data class OperatorEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val url: String,
    val description: String,
    val yearOfFoundation: Int,
)
