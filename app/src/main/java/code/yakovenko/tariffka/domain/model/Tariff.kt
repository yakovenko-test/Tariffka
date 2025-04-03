package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.data.local.entity.TariffEntity
import code.yakovenko.tariffka.domain.model.utils.IdType

data class Tariff(
    val id: IdType,
    val name: String,
    val cost: Int,
    val averageRating: Double,
    val minutesCount: Int,
    val gigabytesCount: Int,
    val operatorId: IdType,
)

fun Tariff.toData() = TariffEntity(id.id)
