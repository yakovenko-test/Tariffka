package code.yakovenko.tariffka.data.mapping

import code.yakovenko.tariffka.data.local.entity.TariffDiscountEntity
import code.yakovenko.tariffka.domain.model.TariffDiscount
import java.time.LocalDateTime

object TariffDiscountMapper {
    fun toDomain(tariffDiscountEntity: TariffDiscountEntity): TariffDiscount {
        return TariffDiscount(
            id = tariffDiscountEntity.id,
            tariffId = tariffDiscountEntity.tariffId,
            newCost = tariffDiscountEntity.newCost,
            activeFrom = tariffDiscountEntity.activeFrom,
            activeUntil = tariffDiscountEntity.activeUntil
        ).apply {
            require(newCost >= 0)
            require(activeFrom <= LocalDateTime.now())
            require(activeFrom <= activeUntil)
        }
    }

    fun toData(tariffDiscount: TariffDiscount): TariffDiscountEntity {
        return TariffDiscountEntity(
            id = tariffDiscount.id,
            tariffId = tariffDiscount.tariffId,
            newCost = tariffDiscount.newCost,
            activeFrom = tariffDiscount.activeFrom,
            activeUntil = tariffDiscount.activeUntil
        )
    }
}
