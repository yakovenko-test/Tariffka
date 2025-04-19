package code.yakovenko.tariffka.data.mapping.operator

import code.yakovenko.tariffka.common.Mapper
import code.yakovenko.tariffka.data.model.OperatorEntity
import code.yakovenko.tariffka.domain.model.Operator

class OperatorEntityToDomainMapper : Mapper<OperatorEntity, Operator> {
    override fun transform(from: OperatorEntity): Operator {
        return Operator(
            id = from.id.value,
            name = from.name,
            countryCode = from.countryCode,
            url = from.url,
            yearOfFoundation = from.yearOfFoundation
        )
    }
}
