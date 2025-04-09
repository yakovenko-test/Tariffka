package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Discount
import kotlinx.coroutines.flow.Flow

interface DiscountRepository {
    suspend fun create(discount: Discount)

    fun readById(discountId: Int): Flow<Discount?>
    fun readByOperatorId(operatorId: Int): Flow<List<Discount>>
    fun readAll(): Flow<List<Discount>>

    suspend fun update(discount: Discount)

    suspend fun deleteById(discountId: Int)
}
