package code.yakovenko.tariffka.data.mapping.discount

import code.yakovenko.tariffka.common.Mapper
import code.yakovenko.tariffka.data.model.DiscountEntity
import code.yakovenko.tariffka.data.table.OperatorsTable
import code.yakovenko.tariffka.domain.model.Discount
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction

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
