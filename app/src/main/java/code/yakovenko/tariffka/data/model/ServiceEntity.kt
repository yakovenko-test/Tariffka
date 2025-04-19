package code.yakovenko.tariffka.data.model

import code.yakovenko.tariffka.data.table.ServicesTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class ServiceEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    var operatorId by ServicesTable.operatorId
    var name by ServicesTable.name
    var description by ServicesTable.description
    var cost by ServicesTable.cost
    var currency by ServicesTable.currency

    companion object : UUIDEntityClass<ServiceEntity>(ServicesTable)
}
