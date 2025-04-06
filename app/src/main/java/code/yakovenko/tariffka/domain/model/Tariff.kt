package code.yakovenko.tariffka.domain.model

data class Tariff(
    val id: Int,
    val operatorId: Int,
    val name: String,
    val cost: Int,
    val minutesCount: Int,
    val gigabytesCount: Int,
    val averageRating: Double,
) {
    init {
        require(name.isNotBlank())
        require(cost >= 0)
        require(minutesCount >= 0)
        require(gigabytesCount >= 0)
        require(averageRating in 0.0..5.0)
    }
}
