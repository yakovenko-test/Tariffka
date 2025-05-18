package test.yakovenko.data.mapping.service

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import test.yakovenko.tariffka.core.Mapper
import test.yakovenko.tariffka.data.model.ServiceEntity
import test.yakovenko.tariffka.data.table.OperatorsTable
import test.yakovenko.tariffka.domain.model.Service

class ServiceToEntityMapper : Mapper<Service, ServiceEntity> {
    override fun transform(from: Service): ServiceEntity = transaction {
        ServiceEntity.new(from.id) {
            operatorId = EntityID(from.operatorId, OperatorsTable)
            name = from.name
            description = from.description
            cost = from.cost
            currency = from.currency
        }
    }
}