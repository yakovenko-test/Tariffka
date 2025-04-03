package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.TariffDiscountDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.TariffDiscount
import code.yakovenko.tariffka.domain.model.toData
import code.yakovenko.tariffka.domain.model.utils.IdType
import code.yakovenko.tariffka.domain.repository.TariffDiscountRepository
import javax.inject.Inject

class TariffDiscountRepositoryImpl @Inject constructor(
    private val tariffDiscountDao: TariffDiscountDao
) : TariffDiscountRepository {
    override suspend fun create(tariffDiscount: TariffDiscount) {
        tariffDiscountDao.insert(tariffDiscount.toData())
    }

    override suspend fun readById(tariffDiscountId: IdType): TariffDiscount? {
        return tariffDiscountDao.selectById(tariffDiscountId)?.toDomain()
    }

    override suspend fun readAll(): List<TariffDiscount> {
        return tariffDiscountDao.selectAll().map { it.toDomain() }
    }

    override suspend fun update(tariffDiscount: TariffDiscount) {
        tariffDiscountDao.update(tariffDiscount.toData())
    }

    override suspend fun deleteById(tariffDiscountId: IdType) {
        tariffDiscountDao.deleteById(tariffDiscountId)
    }
}
