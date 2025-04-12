package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Service
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface ServiceRepository {
    suspend fun create(service: Service)

    fun readById(serviceId: Uuid): Flow<Service?>
    fun readByOperatorId(operatorId: Uuid): Flow<List<Service>>
    fun readAll(): Flow<List<Service>>

    suspend fun update(service: Service)

    suspend fun deleteById(serviceId: Uuid)
}
