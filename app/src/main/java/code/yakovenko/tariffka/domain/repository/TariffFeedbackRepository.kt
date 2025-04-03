package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.model.utils.IdType

interface TariffFeedbackRepository {
    suspend fun create(tariffFeedback: TariffFeedback)

    suspend fun readById(tariffFeedbackId: IdType): TariffFeedback?
    suspend fun readAll(): List<TariffFeedback>

    suspend fun update(tariffFeedback: TariffFeedback)

    suspend fun deleteById(tariffFeedbackId: IdType)
}
