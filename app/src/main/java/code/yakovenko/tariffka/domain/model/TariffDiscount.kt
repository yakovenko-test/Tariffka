package code.yakovenko.tariffka.domain.model

import java.time.LocalDateTime

data class TariffDiscount(
    val id: Int,
    val tariffId: Int,
    val newCost: Int,
    val activeFrom: LocalDateTime,
    val activeUntil: LocalDateTime,
) {
    init {
        require(newCost >= 0)
        require(activeFrom <= LocalDateTime.now())
        require(activeFrom <= activeUntil)
    }
}
