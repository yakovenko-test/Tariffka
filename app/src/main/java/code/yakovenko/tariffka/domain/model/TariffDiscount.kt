package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.data.local.entity.TariffDiscountEntity
import java.time.LocalDateTime

data class TariffDiscount(
    val id: IdType,
    val activeFrom: LocalDateTime,
    val activeUntil: LocalDateTime,
    val newCost: Int,
    val tariff: Tariff,
) {
    init {
        require(activeFrom <= LocalDateTime.now())
        require(activeFrom <= activeUntil)
        require(newCost > 0)
    }
}

fun TariffDiscount.toData() = TariffDiscountEntity(id.id)
