package code.yakovenko.tariffka.domain.model

import java.time.LocalDateTime

data class TariffFeedback(
    val id: Int,
    val tariffId: Int,
    val userId: Int,
    val description: String?,
    val rating: Int,
    val publishedAt: LocalDateTime,
) {
    init {
        require(description?.isNotBlank() == true)
        require(rating in 0..5)
        require(publishedAt <= LocalDateTime.now())
    }
}
