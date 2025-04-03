package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.TariffDiscount
import code.yakovenko.tariffka.domain.model.utils.IdType

interface TariffDiscountRepository {
    suspend fun create(tariffDiscount: TariffDiscount)

    suspend fun readById(tariffDiscountId: IdType): TariffDiscount?
    suspend fun readAll(): List<TariffDiscount>

    suspend fun update(tariffDiscount: TariffDiscount)

    suspend fun deleteById(tariffDiscountId: IdType)
}
