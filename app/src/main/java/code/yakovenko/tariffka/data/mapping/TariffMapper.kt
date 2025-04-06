package code.yakovenko.tariffka.data.mapping

import code.yakovenko.tariffka.data.local.entity.TariffEntity
import code.yakovenko.tariffka.domain.model.Tariff

object TariffMapper {
    fun toDomain(tariffEntity: TariffEntity): Tariff {
        return Tariff(
            id = tariffEntity.id,
            operatorId = tariffEntity.operatorId,
            name = tariffEntity.name,
            cost = tariffEntity.cost,
            minutesCount = tariffEntity.minutesCount,
            gigabytesCount = tariffEntity.gigabytesCount,
            averageRating = tariffEntity.averageRating
        ).apply {
            require(name.isNotBlank())
            require(cost >= 0)
            require(minutesCount >= 0)
            require(gigabytesCount >= 0)
            require(averageRating in 0.0..5.0)
        }
    }

    fun toData(tariff: Tariff): TariffEntity {
        return TariffEntity(
            id = tariff.id,
            operatorId = tariff.operatorId,
            name = tariff.name,
            cost = tariff.cost,
            minutesCount = tariff.minutesCount,
            gigabytesCount = tariff.gigabytesCount,
            averageRating = tariff.averageRating
        )
    }
}
