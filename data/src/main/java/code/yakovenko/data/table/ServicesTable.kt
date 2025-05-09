package code.yakovenko.data.table

import code.yakovenko.tariffka.domain.type.Currency
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

internal object ServicesTable : IdTable<UUID>("services") {
    override val id = uuid("id").entityId()

    val operatorId = reference("operator_id", OperatorsTable)
    val name = text("name")
    val description = text("description")
    val cost = uinteger("cost")
    val currency = enumerationByName<Currency>("currency", Currency.MAX_NAME_LENGTH)
}
