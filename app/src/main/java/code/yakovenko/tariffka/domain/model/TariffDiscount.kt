package code.yakovenko.tariffka.domain.model

import java.time.LocalDateTime

data class TariffDiscount(
    val id: Long,
    val activeFrom: LocalDateTime,
    val activeUntil: LocalDateTime,
    val newCost: Int,
    val tariff: Tariff,
)
