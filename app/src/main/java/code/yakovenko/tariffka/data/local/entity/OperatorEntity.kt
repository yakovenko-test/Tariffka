package code.yakovenko.tariffka.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operators")
data class OperatorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val url: String?,
    val description: String?,
    @ColumnInfo("year_of_foundation")
    val yearOfFoundation: Int?,
)
