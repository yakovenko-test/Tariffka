package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.data.local.entity.TariffDiscountEntity
import code.yakovenko.tariffka.domain.model.utils.IdType
import java.time.LocalDateTime

data class TariffDiscount(
    val id: IdType,
    val activeFrom: LocalDateTime,
    val activeUntil: LocalDateTime,
    val newCost: Int,
    val tariffId: IdType,
)

fun TariffDiscount.toData() = TariffDiscountEntity(id.id)
