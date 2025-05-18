package test.yakovenko.data.model

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import test.yakovenko.data.table.OperatorsTable
import java.util.UUID

class OperatorEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    var name by OperatorsTable.name
    var url by OperatorsTable.url
    var averageEstimation by OperatorsTable.averageEstimation

    companion object : UUIDEntityClass<OperatorEntity>(OperatorsTable)
}
