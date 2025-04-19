package code.yakovenko.tariffka.data.mapping.operator

import code.yakovenko.tariffka.common.Mapper
import code.yakovenko.tariffka.data.model.OperatorEntity
import code.yakovenko.tariffka.domain.model.Operator
import org.jetbrains.exposed.sql.transactions.transaction

class OperatorToEntityMapper : Mapper<Operator, OperatorEntity> {
    override fun transform(from: Operator): OperatorEntity = transaction {
        OperatorEntity.new(from.id) {
            name = from.name
            countryCode = from.countryCode
            url = from.url
            yearOfFoundation = from.yearOfFoundation
        }
    }
}
