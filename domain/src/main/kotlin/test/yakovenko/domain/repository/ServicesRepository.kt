package test.yakovenko.domain.repository

import test.yakovenko.domain.model.Service
import java.util.UUID

interface ServicesRepository {
    suspend fun create(service: Service): Service?
    suspend fun read(serviceId: UUID): Service?
    suspend fun update(service: Service): Service?
    suspend fun delete(serviceId: UUID): Boolean

    suspend fun readAll(): Collection<Service>

    suspend fun findByOperator(operatorId: UUID): Collection<Service>

    suspend fun exists(serviceId: UUID): Boolean
}
