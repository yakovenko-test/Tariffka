package test.yakovenko.data.mapping.discount

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import test.yakovenko.tariffka.core.Mapper
import test.yakovenko.tariffka.data.model.DiscountEntity
import test.yakovenko.tariffka.data.table.OperatorsTable
import test.yakovenko.tariffka.domain.model.Discount

class DiscountToEntityMapper : Mapper<Discount, DiscountEntity> {
    override fun transform(from: Discount): DiscountEntity = transaction {
        DiscountEntity.new(id = from.id) {
            operatorId = EntityID(from.operatorId, OperatorsTable)
            title = from.title
            description = from.description
            activeFrom = from.activeFrom
            activeUntil = from.activeUntil
        }
    }
}
