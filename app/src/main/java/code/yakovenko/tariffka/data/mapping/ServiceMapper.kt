package code.yakovenko.tariffka.data.mapping

import code.yakovenko.tariffka.data.local.entity.ServiceEntity
import code.yakovenko.tariffka.domain.model.Service

object ServiceMapper {
    fun toDomain(serviceEntity: ServiceEntity): Service {
        return Service(
            id = serviceEntity.id,
            operatorId = serviceEntity.operatorId,
            name = serviceEntity.name,
            cost = serviceEntity.cost,
            description = serviceEntity.description
        ).apply {
            require(name.isNotBlank())
            require(cost >= 0)
            require(description.isNotBlank())
        }
    }

    fun toData(service: Service): ServiceEntity {
        return ServiceEntity(
            id = service.id,
            operatorId = service.operatorId,
            name = service.name,
            cost = service.cost,
            description = service.description
        )
    }
}
