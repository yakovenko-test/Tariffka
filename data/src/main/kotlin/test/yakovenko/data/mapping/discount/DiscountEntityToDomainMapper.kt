package test.yakovenko.data.mapping.discount

import test.yakovenko.tariffka.core.Mapper
import test.yakovenko.tariffka.data.model.DiscountEntity
import test.yakovenko.tariffka.domain.model.Discount

class DiscountEntityToDomainMapper : Mapper<DiscountEntity, Discount> {
    override fun transform(from: DiscountEntity): Discount {
        return Discount(
            id = from.id.value,
            operatorId = from.operatorId.value,
            title = from.title,
            description = from.description,
            activeFrom = from.activeFrom,
            activeUntil = from.activeUntil
        )
    }
}
