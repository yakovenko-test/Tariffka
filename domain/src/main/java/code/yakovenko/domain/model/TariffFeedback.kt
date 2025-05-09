package code.yakovenko.domain.model

import code.yakovenko.domain.type.Estimation
import kotlinx.datetime.LocalDateTime
import java.util.UUID

data class TariffFeedback(
    val id: UUID = UUID.randomUUID(),
    val authorId: UUID,
    val tariffId: UUID,
    val description: String?,
    val estimation: Estimation,
    val publishedAt: LocalDateTime,
) {
    init {

    }
}
