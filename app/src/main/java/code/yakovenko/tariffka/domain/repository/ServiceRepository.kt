package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Service
import java.util.UUID

interface ServiceRepository {
    fun create(service: Service): Service

    fun readById(serviceId: UUID): Service?
    fun readByOperatorId(operatorId: UUID): Collection<Service>
    fun readAll(): Collection<Service>

    fun update(service: Service): Service?

    fun deleteById(serviceId: UUID)

    fun containsId(serviceId: UUID): Boolean
    fun notContainsId(serviceId: UUID) = !containsId(serviceId)
}
