package code.yakovenko.tariffka.data.table

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

internal object OperatorsTable : IdTable<UUID>("operators") {
    override val id = uuid("id").entityId()

    val name = text("name")
    val url = text("url").nullable()
}
