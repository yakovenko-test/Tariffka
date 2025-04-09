package code.yakovenko.tariffka.mock

import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.repository.DiscountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class DiscountRepositoryMock : DiscountRepository {
    private val data = mutableListOf<Discount>()
    private val dataFlow = MutableStateFlow<List<Discount>>(emptyList())

    override suspend fun create(discount: Discount) {
        data.add(discount)
        dataFlow.value = data.toList()
    }

    override fun readById(discountId: Int): Flow<Discount?> {
        return dataFlow.map { discounts ->
            discounts.find { it.id == discountId }
        }
    }

    override fun readByOperatorId(operatorId: Int): Flow<List<Discount>> {
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

    override suspend fun deleteById(discountId: Int) {
        data.removeIf { it.id == discountId }
        dataFlow.value = data
    }
}
