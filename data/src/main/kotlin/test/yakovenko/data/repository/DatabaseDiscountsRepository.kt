package test.yakovenko.data.repository

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import test.yakovenko.tariffka.core.transformAll
import test.yakovenko.tariffka.data.mapping.discount.DiscountEntityToDomainMapper
import test.yakovenko.tariffka.data.mapping.discount.DiscountToEntityMapper
import test.yakovenko.tariffka.data.model.DiscountEntity
import test.yakovenko.tariffka.data.table.DiscountsTable
import test.yakovenko.tariffka.data.table.OperatorsTable
import test.yakovenko.tariffka.domain.model.Discount
import test.yakovenko.tariffka.domain.repository.DiscountRepository
import java.util.UUID
import javax.inject.Inject

class DatabaseDiscountsRepository @Inject constructor(
    private val discountToEntityMapper: DiscountToEntityMapper,
    private val discountEntityToDomainMapper: DiscountEntityToDomainMapper
) : DiscountRepository {
    override fun create(discount: Discount): Discount = transaction {
        discountEntityToDomainMapper.transform(
            discountToEntityMapper.transform(discount)
        )
    }

    override fun readById(discountId: UUID): Discount? = transaction {
        DiscountEntity.findById(discountId)?.let { discount ->
            discountEntityToDomainMapper.transform(discount)
        }
    }

    override fun readByOperatorId(operatorId: UUID): Collection<Discount> = transaction {
        discountEntityToDomainMapper.transformAll(
            DiscountEntity.find { DiscountsTable.operatorId eq operatorId }.toList()
        )
    }

    override fun readAll(): Collection<Discount> = transaction {
        discountEntityToDomainMapper.transformAll(
            DiscountEntity.all().toList()
        )
    }

    override fun update(discount: Discount): Discount? = transaction {
        DiscountEntity.findByIdAndUpdate(discount.id) {
            it.operatorId = EntityID(discount.operatorId, OperatorsTable)
            it.title = discount.title
            it.description = discount.description
            it.activeFrom = discount.activeFrom
            it.activeUntil = discount.activeUntil
        }?.let { discountEntity ->
            discountEntityToDomainMapper.transform(discountEntity)
        }
    }

    override fun deleteById(discountId: UUID): Unit = transaction {
        DiscountEntity.findById(discountId)?.delete()
    }

    override fun containsId(discountId: UUID): Boolean = transaction {
        DiscountEntity.findById(discountId) != null
    }
}
