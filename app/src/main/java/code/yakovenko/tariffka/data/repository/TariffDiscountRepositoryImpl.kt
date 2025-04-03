package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.TariffDiscountDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.TariffDiscount
import code.yakovenko.tariffka.domain.repository.TariffDiscountRepository
import javax.inject.Inject

class TariffDiscountRepositoryImpl @Inject constructor(
    private val tariffDiscountDao: TariffDiscountDao
): TariffDiscountRepository {
    override suspend fun getAll(): List<TariffDiscount> {
        return tariffDiscountDao.getAll().map { it.toDomain() }
    }
}
