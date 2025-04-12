package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class TariffFeedbackRepositoryImpl @Inject constructor() : TariffFeedbackRepository {
    private val data = mutableListOf<TariffFeedback>()
    private val dataFlow = MutableStateFlow<List<TariffFeedback>>(emptyList())

    override suspend fun create(tariffFeedback: TariffFeedback) {
        data.add(tariffFeedback)
        dataFlow.value = data.toList()
    }

    override fun readById(tariffFeedbackId: Uuid): Flow<TariffFeedback?> {
        return dataFlow.map { tariffFeedbacks ->
            tariffFeedbacks.find { it.id == tariffFeedbackId }
        }
    }

    override fun readByTariffId(tariffId: Uuid): Flow<List<TariffFeedback>> {
        return dataFlow.map { tariffFeedbacks ->
            tariffFeedbacks.filter { it.tariffId == tariffId }
        }
    }

    override fun readAll(): Flow<List<TariffFeedback>> {
        return dataFlow.asStateFlow()
    }

    override suspend fun update(tariffFeedback: TariffFeedback) {
        val index = data.indexOfFirst { it.id == tariffFeedback.id }

        if (index != -1) {
            data[index] = tariffFeedback
            dataFlow.value = data.toList()
        }
    }

    override suspend fun deleteById(tariffFeedbackId: Uuid) {
        data.removeIf { it.id == tariffFeedbackId }
        dataFlow.value = data
    }
}
