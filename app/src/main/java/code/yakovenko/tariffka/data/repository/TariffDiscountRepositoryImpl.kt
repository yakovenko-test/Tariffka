package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.TariffDiscountDao
import code.yakovenko.tariffka.data.mapping.TariffDiscountMapper
import code.yakovenko.tariffka.domain.model.TariffDiscount
import code.yakovenko.tariffka.domain.repository.TariffDiscountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TariffDiscountRepositoryImpl @Inject constructor(
    private val tariffDiscountDao: TariffDiscountDao
) : TariffDiscountRepository {
    override suspend fun create(tariffDiscount: TariffDiscount) {
        tariffDiscountDao.insert(TariffDiscountMapper.toData(tariffDiscount))
    }

    override suspend fun readById(tariffDiscountId: Int): TariffDiscount? {
        return tariffDiscountDao.selectById(tariffDiscountId)?.let {
            TariffDiscountMapper.toDomain(it)
        }
    }

    override suspend fun readAll(): Flow<List<TariffDiscount>> {
        return tariffDiscountDao.selectAll().map { entities ->
            entities.map { TariffDiscountMapper.toDomain(it) }
        }
    }

    override suspend fun update(tariffDiscount: TariffDiscount) {
        tariffDiscountDao.update(TariffDiscountMapper.toData(tariffDiscount))
    }

    override suspend fun deleteById(tariffDiscountId: Int) {
        tariffDiscountDao.deleteById(tariffDiscountId)
    }
}
