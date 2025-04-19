package code.yakovenko.tariffka.data.mapping.service

import code.yakovenko.tariffka.common.Mapper
import code.yakovenko.tariffka.data.model.ServiceEntity
import code.yakovenko.tariffka.domain.model.Service

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
