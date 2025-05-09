package code.yakovenko.data.mapping.discount

import code.yakovenko.tariffka.core.Mapper
import code.yakovenko.tariffka.data.model.DiscountEntity
import code.yakovenko.tariffka.domain.model.Discount

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
