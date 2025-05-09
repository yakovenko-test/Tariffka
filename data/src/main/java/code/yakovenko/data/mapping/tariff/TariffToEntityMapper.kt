package code.yakovenko.data.mapping.tariff

import code.yakovenko.tariffka.core.Mapper
import code.yakovenko.tariffka.data.model.TariffEntity
import code.yakovenko.tariffka.data.table.OperatorsTable
import code.yakovenko.tariffka.domain.model.Tariff
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction

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
