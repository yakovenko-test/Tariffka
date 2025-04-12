package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Discount
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface DiscountRepository {
    suspend fun create(discount: Discount)

    fun readById(discountId: Uuid): Flow<Discount?>
    fun readByOperatorId(operatorId: Uuid): Flow<List<Discount>>
    fun readAll(): Flow<List<Discount>>

    suspend fun update(discount: Discount)

    suspend fun deleteById(discountId: Uuid)
}
