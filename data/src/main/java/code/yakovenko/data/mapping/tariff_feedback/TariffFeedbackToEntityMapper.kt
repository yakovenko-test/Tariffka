package code.yakovenko.data.mapping.tariff_feedback

import code.yakovenko.tariffka.core.Mapper
import code.yakovenko.tariffka.data.model.TariffFeedbackEntity
import code.yakovenko.tariffka.data.table.TariffsTable
import code.yakovenko.tariffka.data.table.UsersTable
import code.yakovenko.tariffka.domain.model.TariffFeedback
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction

class TariffFeedbackToEntityMapper : Mapper<TariffFeedback, TariffFeedbackEntity> {
    override fun transform(from: TariffFeedback): TariffFeedbackEntity = transaction {
        TariffFeedbackEntity.new(from.id) {
            authorId = EntityID(from.authorId, UsersTable)
            tariffId = EntityID(from.tariffId, TariffsTable)
            description = from.description
            estimation = from.estimation
            publishedAt = from.publishedAt
        }
    }
}
