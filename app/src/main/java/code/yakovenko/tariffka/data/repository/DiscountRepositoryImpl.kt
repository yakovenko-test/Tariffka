package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.DiscountDao
import code.yakovenko.tariffka.data.mapping.DiscountMapper
import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.repository.DiscountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DiscountRepositoryImpl(
    private val discountDao: DiscountDao
) : DiscountRepository {
    override suspend fun create(discount: Discount) {
        discountDao.insertDiscount(DiscountMapper.toData(discount))
    }

    override fun readById(discountId: Int): Flow<Discount?> {
        return discountDao.selectDiscountById(discountId).map { entity ->
            entity?.let { DiscountMapper.toDomain(it) }
        }
    }

    override fun readByOperatorId(operatorId: Int): Flow<List<Discount>> {
        return discountDao.selectDiscountsByOperatorId(operatorId).map { entities ->
            entities.map { DiscountMapper.toDomain(it) }
        }
    }

    override fun readAll(): Flow<List<Discount>> {
        return discountDao.selectAllDiscounts().map { entities ->
            entities.map { DiscountMapper.toDomain(it) }
        }
    }

    override suspend fun update(discount: Discount) {
        discountDao.updateDiscount(DiscountMapper.toData(discount))
    }

    override suspend fun deleteById(discountId: Int) {
        discountDao.deleteDiscountById(discountId)
    }
}
