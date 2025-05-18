package test.yakovenko.data.mapping.tariff

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import test.yakovenko.tariffka.core.Mapper
import test.yakovenko.tariffka.data.model.TariffEntity
import test.yakovenko.tariffka.data.table.OperatorsTable
import test.yakovenko.tariffka.domain.model.Tariff

class TariffToEntityMapper : Mapper<Tariff, TariffEntity> {
    override fun transform(from: Tariff): TariffEntity = transaction {
        TariffEntity.new(from.id) {
            operatorId = EntityID(from.operatorId, OperatorsTable)
            name = from.name
            description = from.description
            cost = from.cost
            currency = from.currency
            minutesCount = from.minutesCount
            gigabytesCount = from.gigabytesCount
            averageEstimation = from.averageEstimation
        }
    }
}
