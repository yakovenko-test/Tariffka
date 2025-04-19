package code.yakovenko.tariffka.data.mapping.service

import code.yakovenko.tariffka.common.Mapper
import code.yakovenko.tariffka.data.model.ServiceEntity
import code.yakovenko.tariffka.data.table.OperatorsTable
import code.yakovenko.tariffka.domain.model.Service
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction

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