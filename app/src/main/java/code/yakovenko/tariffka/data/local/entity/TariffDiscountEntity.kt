package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import code.yakovenko.tariffka.MOCKED_TARIFF_DISCOUNT

@Entity(tableName = "tariff_discounts")
data class TariffDiscountEntity(
    @PrimaryKey val id: Long,
)

fun TariffDiscountEntity.toDomain() = MOCKED_TARIFF_DISCOUNT
