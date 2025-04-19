package code.yakovenko.tariffka.data.model

import code.yakovenko.tariffka.data.table.OperatorsTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class OperatorEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    var name by OperatorsTable.name
    var countryCode by OperatorsTable.countryCode
    var url by OperatorsTable.url
    var yearOfFoundation by OperatorsTable.yearOfFoundation

    companion object : UUIDEntityClass<OperatorEntity>(OperatorsTable)
}
