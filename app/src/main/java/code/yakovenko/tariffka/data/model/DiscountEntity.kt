package code.yakovenko.tariffka.data.model

import code.yakovenko.tariffka.data.table.DiscountsTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class DiscountEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    var operatorId by DiscountsTable.operatorId
    var title by DiscountsTable.title
    var description by DiscountsTable.description
    var activeFrom by DiscountsTable.activeFrom
    var activeUntil by DiscountsTable.activeUntil

    companion object : UUIDEntityClass<DiscountEntity>(DiscountsTable)
}
