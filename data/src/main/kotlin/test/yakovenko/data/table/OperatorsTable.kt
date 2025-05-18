package test.yakovenko.data.table

import org.jetbrains.exposed.dao.id.IdTable
import java.util.UUID

internal object OperatorsTable : IdTable<UUID>("operators") {
    override val id = uuid("id").entityId()

    val name = text("name")
    val url = text("url")
    val averageEstimation = double("average_estimation").nullable()
}
