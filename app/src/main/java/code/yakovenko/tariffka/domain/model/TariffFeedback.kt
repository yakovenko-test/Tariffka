package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.common.validation.LocalDateTimeValidator
import code.yakovenko.tariffka.common.validation.StringFieldValidator
import code.yakovenko.tariffka.common.validation.validate
import code.yakovenko.tariffka.domain.type.Estimation
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
        validate(description, "Description", StringFieldValidator)
        validate(publishedAt, "PublishedAt", LocalDateTimeValidator)
    }
}
