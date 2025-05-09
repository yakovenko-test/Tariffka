package code.yakovenko.data.mapping.tariff

import code.yakovenko.tariffka.core.Mapper
import code.yakovenko.tariffka.data.model.TariffEntity
import code.yakovenko.tariffka.domain.model.Tariff

class TariffEntityToDomainMapper : Mapper<TariffEntity, Tariff> {
    override fun transform(from: TariffEntity): Tariff {
        return Tariff(
            id = from.id.value,
            operatorId = from.operatorId.value,
            name = from.name,
            description = from.description,
            cost = from.cost,
            currency = from.currency,
            minutesCount = from.minutesCount,
            gigabytesCount = from.gigabytesCount,
            averageEstimation = from.averageEstimation
        )
    }
}
