package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.TariffDiscount

interface TariffDiscountRepository {
    suspend fun getAll(): List<TariffDiscount>
}
