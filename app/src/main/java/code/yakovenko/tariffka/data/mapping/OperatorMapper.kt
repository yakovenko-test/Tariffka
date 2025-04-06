package code.yakovenko.tariffka.data.mapping

import android.util.Patterns
import code.yakovenko.tariffka.data.local.entity.OperatorEntity
import code.yakovenko.tariffka.domain.model.Operator
import java.time.Year

object OperatorMapper {
    fun toDomain(operatorEntity: OperatorEntity): Operator {
        return Operator(
            id = operatorEntity.id,
            name = operatorEntity.name,
            url = operatorEntity.url,
            description = operatorEntity.description,
            yearOfFoundation = operatorEntity.yearOfFoundation
        ).apply {
            require(name.isNotBlank())
            require(Patterns.WEB_URL.matcher(url).matches())
            require(description.isNotBlank())
            require(yearOfFoundation in 0..Year.now().value)
        }
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
