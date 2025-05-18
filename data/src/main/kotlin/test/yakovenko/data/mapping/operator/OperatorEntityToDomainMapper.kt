package test.yakovenko.data.mapping.operator

import test.yakovenko.common.Mapper
import test.yakovenko.data.model.OperatorEntity
import test.yakovenko.domain.model.Operator
import test.yakovenko.domain.model.type.AverageEstimation
import java.net.URI

class OperatorEntityToDomainMapper : Mapper<OperatorEntity, Operator> {
    override fun transform(from: OperatorEntity): Operator {
        return Operator(
            id = from.id.value,
            name = from.name,
            url = URI(from.url).toURL(),
            averageEstimation = AverageEstimation(from.averageEstimation)
        )
    }
}
