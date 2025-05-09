package code.yakovenko.data.table

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.net.IDN
import java.util.UUID

internal object TariffFeedbacksTable : IdTable<UUID>("tariff_feedbacks") {
    override val id = uuid("id").entityId()

    val authorId = reference("author_id", UsersTable)
    val tariffId = reference("tariff_id", TariffsTable)
    val description = text("description").nullable()
    val estimation = ubyte("estimation")
    val publishedAt = datetime("published_at")
}
