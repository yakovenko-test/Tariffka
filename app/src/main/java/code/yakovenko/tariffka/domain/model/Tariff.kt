package code.yakovenko.tariffka.domain.model

data class Tariff(
    val id: Long,
    val name: String,
    val cost: Int,
    val averageRating: Double,
    val minutesCount: Int,
    val gigabytesCount: Int,
    val operator: Operator,
)
