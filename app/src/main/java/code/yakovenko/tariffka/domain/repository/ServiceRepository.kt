package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Service
import kotlinx.coroutines.flow.Flow

interface ServiceRepository {
    suspend fun create(service: Service)

    fun readById(serviceId: Int): Flow<Service?>
    fun readByOperatorId(operatorId: Int): Flow<List<Service>>
    fun readAll(): Flow<List<Service>>

    suspend fun update(service: Service)

    suspend fun deleteById(serviceId: Int)
}
