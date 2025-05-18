package test.yakovenko.data.mapping.operator

import org.jetbrains.exposed.sql.transactions.transaction
import test.yakovenko.common.Mapper
import test.yakovenko.data.model.OperatorEntity
import test.yakovenko.domain.model.Operator
import test.yakovenko.tariffka.core.Mapper
import test.yakovenko.tariffka.data.model.OperatorEntity
import test.yakovenko.tariffka.domain.model.Operator

class OperatorToEntityMapper : Mapper<Operator, OperatorEntity> {
    override fun transform(from: Operator): OperatorEntity = transaction {
        OperatorEntity.new(from.id) {
            name = from.name
            url = from.url.toString()
            averageEstimation = from.averageEstimation?.value
        }
    }
}
