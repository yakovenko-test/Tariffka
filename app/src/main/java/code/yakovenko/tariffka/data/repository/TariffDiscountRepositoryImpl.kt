package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.TariffDiscountDao
import code.yakovenko.tariffka.data.mapping.TariffDiscountMapper
import code.yakovenko.tariffka.domain.model.TariffDiscount
import code.yakovenko.tariffka.domain.repository.TariffDiscountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TariffDiscountRepositoryImpl(
    private val tariffDiscountDao: TariffDiscountDao
) : TariffDiscountRepository {
    override suspend fun create(tariffDiscount: TariffDiscount) {
        tariffDiscountDao.insertTariffDiscount(TariffDiscountMapper.toData(tariffDiscount))
    }

    override fun readById(tariffDiscountId: Int): Flow<TariffDiscount?> {
        return tariffDiscountDao.selectTariffDiscountById(tariffDiscountId).map { entity ->
            entity?.let { TariffDiscountMapper.toDomain(it) }
        }
    }

    override fun readByOperatorId(operatorId: Int): Flow<List<TariffDiscount>> {
        return tariffDiscountDao.selectTariffDiscountsByOperatorId(operatorId).map { entities ->
            entities.map { TariffDiscountMapper.toDomain(it) }
        }
    }

    override fun readAll(): Flow<List<TariffDiscount>> {
        return tariffDiscountDao.selectAllTariffDiscounts().map { entities ->
            entities.map { TariffDiscountMapper.toDomain(it) }
        }
    }

    override suspend fun update(tariffDiscount: TariffDiscount) {
        tariffDiscountDao.updateTariffDiscount(TariffDiscountMapper.toData(tariffDiscount))
    }

    override suspend fun deleteById(tariffDiscountId: Int) {
        tariffDiscountDao.deleteTariffDiscountById(tariffDiscountId)
    }
}
