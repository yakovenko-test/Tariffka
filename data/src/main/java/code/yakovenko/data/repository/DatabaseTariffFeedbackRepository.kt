package code.yakovenko.data.repository

import code.yakovenko.tariffka.core.transformAll
import code.yakovenko.tariffka.data.mapping.tariff_feedback.TariffFeedbackEntityToDomainMapper
import code.yakovenko.tariffka.data.mapping.tariff_feedback.TariffFeedbackToEntityMapper
import code.yakovenko.tariffka.data.model.TariffFeedbackEntity
import code.yakovenko.tariffka.data.table.TariffFeedbacksTable
import code.yakovenko.tariffka.data.table.TariffsTable
import code.yakovenko.tariffka.data.table.UsersTable
import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID
import javax.inject.Inject

class DatabaseTariffFeedbackRepository @Inject constructor(
    private val tariffFeedbackToEntityMapper: TariffFeedbackToEntityMapper,
    private val tariffFeedbackEntityToDomainMapper: TariffFeedbackEntityToDomainMapper
) : TariffFeedbackRepository {
    override fun create(tariffFeedback: TariffFeedback): TariffFeedback = transaction {
        tariffFeedbackEntityToDomainMapper.transform(
            tariffFeedbackToEntityMapper.transform(tariffFeedback)
        )
    }

    override fun readById(tariffFeedbackId: UUID): TariffFeedback? = transaction {
        TariffFeedbackEntity.findById(tariffFeedbackId)?.let { tariffFeedback ->
            tariffFeedbackEntityToDomainMapper.transform(tariffFeedback)
        }
    }

    override fun readByTariffId(tariffId: UUID): Collection<TariffFeedback> = transaction {
        tariffFeedbackEntityToDomainMapper.transformAll(
            TariffFeedbackEntity.find { TariffFeedbacksTable.tariffId eq tariffId }.toList()
        )
    }

    override fun readAll(): Collection<TariffFeedback> = transaction {
        tariffFeedbackEntityToDomainMapper.transformAll(
            TariffFeedbackEntity.all().toList()
        )
    }

    override fun update(tariffFeedback: TariffFeedback): TariffFeedback? = transaction {
        TariffFeedbackEntity.findByIdAndUpdate(tariffFeedback.id) {
            it.tariffId = EntityID(tariffFeedback.tariffId, TariffsTable)
            it.authorId = EntityID(tariffFeedback.authorId, UsersTable)
            it.description = tariffFeedback.description
            it.estimation = tariffFeedback.estimation
            it.publishedAt = tariffFeedback.publishedAt
        }?.let { tariffFeedback ->
            tariffFeedbackEntityToDomainMapper.transform(tariffFeedback)
        }
    }

    override fun deleteById(tariffFeedbackId: UUID): Unit = transaction {
        TariffFeedbackEntity.findById(tariffFeedbackId)?.delete()
    }

    override fun containsId(tariffFeedbackId: UUID): Boolean = transaction {
        TariffFeedbackEntity.findById(tariffFeedbackId) != null
    }
}
