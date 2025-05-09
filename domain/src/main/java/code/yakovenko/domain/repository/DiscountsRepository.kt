package code.yakovenko.domain.repository

import code.yakovenko.domain.model.Discount
import java.util.UUID

interface DiscountsRepository {
    fun create(discount: Discount): Discount

    fun readById(discountId: UUID): Discount?
    fun readByOperatorId(operatorId: UUID): Collection<Discount>
    fun readAll(): Collection<Discount>

    fun update(discount: Discount): Discount?

    fun deleteById(discountId: UUID)

    fun containsId(discountId: UUID): Boolean
    fun notContainsId(discountId: UUID) = !containsId(discountId)
}
