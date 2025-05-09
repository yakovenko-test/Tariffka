package code.yakovenko.domain.repository

import code.yakovenko.domain.model.TariffFeedback
import java.util.UUID

interface TariffFeedbacksRepository {
    fun create(tariffFeedback: TariffFeedback): TariffFeedback

    fun readById(tariffFeedbackId: UUID): TariffFeedback?
    fun readByTariffId(tariffId: UUID): Collection<TariffFeedback>
    fun readAll(): Collection<TariffFeedback>

    fun update(tariffFeedback: TariffFeedback): TariffFeedback?

    fun deleteById(tariffFeedbackId: UUID)

    fun containsId(tariffFeedbackId: UUID): Boolean
    fun notContainsId(tariffFeedbackId: UUID) = !containsId(tariffFeedbackId)
}
