package code.yakovenko.tariffka.data.table

import code.yakovenko.tariffka.domain.type.Currency
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

internal object TariffsTable : IdTable<UUID>("tariffs") {
    override val id = uuid("id").entityId()

    val operatorId = reference("operator_id", OperatorsTable)
    val name = text("name")
    val description = text("description")
    val cost = uinteger("cost")
    val currency = enumerationByName<Currency>("currency", Currency.MAX_NAME_LENGTH)
    val minutesCount = uinteger("minutes_count").nullable()
    val gigabytesCount = uinteger("gigabytes_count").nullable()
    val averageEstimation = double("average_estimation").nullable()
}
