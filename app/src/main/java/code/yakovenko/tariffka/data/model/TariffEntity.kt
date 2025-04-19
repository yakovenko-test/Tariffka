package code.yakovenko.tariffka.data.model

import code.yakovenko.tariffka.data.table.TariffsTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class TariffEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    var operatorId by TariffsTable.operatorId
    var name by TariffsTable.name
    var description by TariffsTable.description
    var cost by TariffsTable.cost
    var currency by TariffsTable.currency
    var minutesCount by TariffsTable.minutesCount
    var gigabytesCount by TariffsTable.gigabytesCount
    var averageEstimation by TariffsTable.averageEstimation

    companion object : UUIDEntityClass<TariffEntity>(TariffsTable)
}
