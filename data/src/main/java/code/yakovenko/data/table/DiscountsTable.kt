package code.yakovenko.data.table

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.util.UUID

internal object DiscountsTable : IdTable<UUID>("discounts") {
    override val id = uuid("id").entityId()

    val operatorId = reference("operator_id", OperatorsTable)
    val title = text("title")
    val description = text("description")
    val activeFrom = datetime("active_from")
    val activeUntil = datetime("active_until").nullable()
}
