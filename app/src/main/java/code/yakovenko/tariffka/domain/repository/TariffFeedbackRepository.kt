package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.TariffFeedback
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface TariffFeedbackRepository {
    suspend fun create(tariffFeedback: TariffFeedback)

    fun readById(tariffFeedbackId: Uuid): Flow<TariffFeedback?>
    fun readByTariffId(tariffId: Uuid): Flow<List<TariffFeedback>>
    fun readAll(): Flow<List<TariffFeedback>>

    suspend fun update(tariffFeedback: TariffFeedback)

    suspend fun deleteById(tariffFeedbackId: Uuid)
}
