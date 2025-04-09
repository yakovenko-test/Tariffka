package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.TariffDiscount
import kotlinx.coroutines.flow.Flow

interface TariffDiscountRepository {
    suspend fun create(tariffDiscount: TariffDiscount)

    fun readById(tariffDiscountId: Int): Flow<TariffDiscount?>
    fun readByOperatorId(operatorId: Int): Flow<List<TariffDiscount>>
    fun readAll(): Flow<List<TariffDiscount>>

    suspend fun update(tariffDiscount: TariffDiscount)

    suspend fun deleteById(tariffDiscountId: Int)
}
