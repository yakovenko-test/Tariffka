package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Service
import kotlinx.coroutines.flow.Flow

interface ServiceRepository {
    suspend fun create(service: Service)

    suspend fun readById(optionId: Int): Service?
    suspend fun readAll(): Flow<List<Service>>

    suspend fun update(service: Service)

    suspend fun deleteById(optionId: Int)
}
