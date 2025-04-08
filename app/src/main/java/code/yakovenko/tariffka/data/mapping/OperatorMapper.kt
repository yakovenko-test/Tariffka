package code.yakovenko.tariffka.data.mapping

import code.yakovenko.tariffka.data.local.entity.OperatorEntity
import code.yakovenko.tariffka.domain.model.Operator

object OperatorMapper {
    fun toDomain(operatorEntity: OperatorEntity): Operator {
        return Operator(
            id = operatorEntity.id,
            name = operatorEntity.name,
            url = operatorEntity.url,
            description = operatorEntity.description,
            yearOfFoundation = operatorEntity.yearOfFoundation
        )
    }

    fun toData(operator: Operator): OperatorEntity {
        return OperatorEntity(
            id = operator.id,
            name = operator.name,
            url = operator.url,
            description = operator.description,
            yearOfFoundation = operator.yearOfFoundation
        )
    }
}
