package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.TariffFeedback
import kotlinx.coroutines.flow.Flow

interface TariffFeedbackRepository {
    suspend fun create(tariffFeedback: TariffFeedback)

    fun readById(tariffFeedbackId: Int): Flow<TariffFeedback?>
    fun readByTariffId(tariffId: Int): Flow<List<TariffFeedback>>
    fun readAll(): Flow<List<TariffFeedback>>

    suspend fun update(tariffFeedback: TariffFeedback)

    suspend fun deleteById(tariffFeedbackId: Int)
}
