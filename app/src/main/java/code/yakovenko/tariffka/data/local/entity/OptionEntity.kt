package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import code.yakovenko.tariffka.MOCKED_OPTION

@Entity(tableName = "options")
data class OptionEntity(
    @PrimaryKey val id: Long,
)

fun OptionEntity.toDomain() = MOCKED_OPTION
