package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.repository.DiscountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DiscountRepositoryImpl @Inject constructor() : DiscountRepository {
    private val data = mutableListOf<Discount>()
    private val dataFlow = MutableStateFlow<List<Discount>>(emptyList())

    override suspend fun create(discount: Discount) {
        data.add(discount)
        dataFlow.value = data.toList()
    }

    override fun readById(discountId: Uuid): Flow<Discount?> {
        return dataFlow.map { discounts ->
            discounts.find { it.id == discountId }
        }
    }

    override fun readByOperatorId(operatorId: Uuid): Flow<List<Discount>> {
        return dataFlow.map { discounts ->
            discounts.filter { it.operatorId == operatorId }
        }
    }

    override fun readAll(): Flow<List<Discount>> {
        return dataFlow.asStateFlow()
    }

    override suspend fun update(discount: Discount) {
        val index = data.indexOfFirst { it.id == discount.id }

        if (index != -1) {
            data[index] = discount
            dataFlow.value = data.toList()
        }
    }

    override suspend fun deleteById(discountId: Uuid) {
        data.removeIf { it.id == discountId }
        dataFlow.value = data
    }
}
