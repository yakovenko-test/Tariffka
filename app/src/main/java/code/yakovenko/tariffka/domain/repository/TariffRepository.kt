package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Tariff
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface TariffRepository {
    suspend fun create(tariff: Tariff)

    fun readById(tariffId: Uuid): Flow<Tariff?>
    fun readByOperatorId(operatorId: Uuid): Flow<List<Tariff>>
    fun readAll(): Flow<List<Tariff>>

    suspend fun update(tariff: Tariff)

    suspend fun deleteById(tariffId: Uuid)
}
