package code.yakovenko.tariffka.domain.model

import java.time.LocalDateTime

data class TariffFeedback(
    val id: Long,
    val description: String?,
    val rating: Int,
    val publishedAt: LocalDateTime,
    val tariff: Tariff,
    val user: User,
)
