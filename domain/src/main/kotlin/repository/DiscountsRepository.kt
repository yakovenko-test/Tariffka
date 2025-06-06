package repository

import model.Discount
import java.util.*

interface DiscountsRepository {
    suspend fun create(discount: Discount): Discount?
    suspend fun read(discountId: UUID): Discount?
    suspend fun update(discount: Discount): Discount?
    suspend fun delete(discountId: UUID): Boolean

    suspend fun readAll(): Collection<Discount>

    suspend fun findByOperator(operatorId: UUID): Collection<Discount>

    suspend fun exists(discountId: UUID): Boolean
}
