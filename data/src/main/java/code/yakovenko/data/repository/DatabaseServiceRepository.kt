package code.yakovenko.data.repository

import code.yakovenko.tariffka.core.transformAll
import code.yakovenko.tariffka.data.mapping.service.ServiceEntityToDomainMapper
import code.yakovenko.tariffka.data.mapping.service.ServiceToEntityMapper
import code.yakovenko.tariffka.data.model.ServiceEntity
import code.yakovenko.tariffka.data.table.OperatorsTable
import code.yakovenko.tariffka.data.table.ServicesTable
import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID
import javax.inject.Inject

class DatabaseServiceRepository @Inject constructor(
    private val serviceToEntityMapper: ServiceToEntityMapper,
    private val serviceEntityToDomainMapper: ServiceEntityToDomainMapper
) : ServiceRepository {
    override fun create(service: Service): Service = transaction {
        serviceEntityToDomainMapper.transform(
            serviceToEntityMapper.transform(service)
        )
    }

    override fun readById(serviceId: UUID): Service? = transaction {
        ServiceEntity.findById(serviceId)?.let { service ->
            serviceEntityToDomainMapper.transform(service)
        }
    }

    override fun readByOperatorId(operatorId: UUID): Collection<Service> = transaction {
        serviceEntityToDomainMapper.transformAll(
            ServiceEntity.find { ServicesTable.operatorId eq operatorId }.toList()
        )
    }

    override fun readAll(): Collection<Service> = transaction {
        serviceEntityToDomainMapper.transformAll(
            ServiceEntity.all().toList()
        )
    }

    override fun update(service: Service): Service? = transaction {
        ServiceEntity.findByIdAndUpdate(service.id) {
            it.operatorId = EntityID(service.operatorId, OperatorsTable)
            it.name = service.name
            it.description = service.description
            it.cost = service.cost
            it.currency = service.currency
        }?.let { serviceEntity ->
            serviceEntityToDomainMapper.transform(serviceEntity)
        }
    }

    override fun deleteById(serviceId: UUID): Unit = transaction {
        ServiceEntity.findById(serviceId)?.delete()
    }

    override fun containsId(serviceId: UUID): Boolean = transaction {
        ServiceEntity.findById(serviceId) != null
    }
}
