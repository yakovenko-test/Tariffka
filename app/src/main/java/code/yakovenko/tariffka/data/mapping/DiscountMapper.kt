package code.yakovenko.tariffka.data.mapping

import code.yakovenko.tariffka.data.local.entity.DiscountEntity
import code.yakovenko.tariffka.domain.model.Discount

object DiscountMapper {
    fun toDomain(discountEntity: DiscountEntity): Discount {
        return Discount(
            id = discountEntity.id,
            operatorId = discountEntity.operatorId,
            name = discountEntity.name,
            description = discountEntity.description,
            activeFrom = discountEntity.activeFrom,
            activeUntil = discountEntity.activeUntil
        )
    }

    fun toData(discount: Discount): DiscountEntity {
        return DiscountEntity(
            id = discount.id,
            operatorId = discount.operatorId,
            name = discount.name,
            description = discount.description,
            activeFrom = discount.activeFrom,
            activeUntil = discount.activeUntil
        )
    }
}
