package code.yakovenko.data.repository

import code.yakovenko.tariffka.core.transformAll
import code.yakovenko.tariffka.data.mapping.discount.DiscountEntityToDomainMapper
import code.yakovenko.tariffka.data.mapping.discount.DiscountToEntityMapper
import code.yakovenko.tariffka.data.model.DiscountEntity
import code.yakovenko.tariffka.data.table.DiscountsTable
import code.yakovenko.tariffka.data.table.OperatorsTable
import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.repository.DiscountRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID
import javax.inject.Inject

class DatabaseDiscountRepository @Inject constructor(
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
