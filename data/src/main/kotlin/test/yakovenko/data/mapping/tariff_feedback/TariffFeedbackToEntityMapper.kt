package test.yakovenko.data.mapping.tariff_feedback

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import test.yakovenko.tariffka.core.Mapper
import test.yakovenko.tariffka.data.model.TariffFeedbackEntity
import test.yakovenko.tariffka.data.table.TariffsTable
import test.yakovenko.tariffka.data.table.UsersTable
import test.yakovenko.tariffka.domain.model.TariffFeedback

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
