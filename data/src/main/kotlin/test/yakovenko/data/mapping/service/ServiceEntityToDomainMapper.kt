package test.yakovenko.data.mapping.service

import test.yakovenko.tariffka.core.Mapper
import test.yakovenko.tariffka.data.model.ServiceEntity
import test.yakovenko.tariffka.domain.model.Service

class ServiceEntityToDomainMapper : Mapper<ServiceEntity, Service> {
    override fun transform(from: ServiceEntity): Service {
        return Service(
            id = from.id.value,
            operatorId = from.operatorId.value,
            name = from.name,
            description = from.description,
            cost = from.cost,
            currency = from.currency
        )
    }
}
