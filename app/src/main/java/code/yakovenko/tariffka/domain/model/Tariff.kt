package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.data.local.entity.TariffEntity

data class Tariff(
    val id: IdType,
    val name: String,
    val cost: Int,
    val averageRating: Double,
    val minutesCount: Int,
    val gigabytesCount: Int,
    val operator: Operator,
) {
    init {
        require(cost > 0)
        require(averageRating in 0.0..5.0)
        require(minutesCount > 0)
        require(gigabytesCount > 0)
    }
}

fun Tariff.toData() = TariffEntity(id.id)
