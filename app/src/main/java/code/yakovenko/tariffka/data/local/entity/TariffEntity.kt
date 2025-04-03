package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import code.yakovenko.tariffka.MOCKED_TARIFF

@Entity(tableName = "tariffs")
data class TariffEntity(
    @PrimaryKey val id: Long,
)

fun TariffEntity.toDomain() = MOCKED_TARIFF
