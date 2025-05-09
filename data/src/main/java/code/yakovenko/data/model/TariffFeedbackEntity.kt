package code.yakovenko.data.model

import code.yakovenko.tariffka.data.table.TariffFeedbacksTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class TariffFeedbackEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    var authorId by TariffFeedbacksTable.authorId
    var tariffId by TariffFeedbacksTable.tariffId
    var description by TariffFeedbacksTable.description
    var estimation by TariffFeedbacksTable.estimation
    var publishedAt by TariffFeedbacksTable.publishedAt

    companion object : UUIDEntityClass<TariffFeedbackEntity>(TariffFeedbacksTable)
}
