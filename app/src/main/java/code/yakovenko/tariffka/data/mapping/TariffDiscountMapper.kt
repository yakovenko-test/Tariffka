package code.yakovenko.tariffka.data.mapping

import code.yakovenko.tariffka.data.local.entity.TariffDiscountEntity
import code.yakovenko.tariffka.domain.model.TariffDiscount

object TariffDiscountMapper {
    fun toDomain(tariffDiscountEntity: TariffDiscountEntity): TariffDiscount {
        return TariffDiscount(
            id = tariffDiscountEntity.id,
            tariffId = tariffDiscountEntity.tariffId,
            newCost = tariffDiscountEntity.newCost,
            activeFrom = tariffDiscountEntity.activeFrom,
            activeUntil = tariffDiscountEntity.activeUntil
        )
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
