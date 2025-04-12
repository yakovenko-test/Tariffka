package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.TariffRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class TariffRepositoryImpl @Inject constructor() : TariffRepository {
    private val data = mutableListOf<Tariff>()
    private val dataFlow = MutableStateFlow<List<Tariff>>(emptyList())

    override suspend fun create(tariff: Tariff) {
        data.add(tariff)
        dataFlow.value = data.toList()
    }

    override fun readById(tariffId: Uuid): Flow<Tariff?> {
        return dataFlow.map { tariffs ->
            tariffs.find { it.id == tariffId }
        }
    }

    override fun readByOperatorId(operatorId: Uuid): Flow<List<Tariff>> {
        return dataFlow.map { tariffs ->
            tariffs.filter { it.operatorId == operatorId }
        }
    }

    override fun readAll(): Flow<List<Tariff>> {
        return dataFlow.asStateFlow()
    }

    override suspend fun update(tariff: Tariff) {
        val index = data.indexOfFirst { it.id == tariff.id }

        if (index != -1) {
            data[index] = tariff
            dataFlow.value = data.toList()
        }
    }

    override suspend fun deleteById(tariffId: Uuid) {
        data.removeIf { it.id == tariffId }
        dataFlow.value = data
    }
}
