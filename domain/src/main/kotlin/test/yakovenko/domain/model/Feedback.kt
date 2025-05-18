package test.yakovenko.domain.model

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import test.yakovenko.domain.model.type.Estimation
import test.yakovenko.domain.validation.validate
import test.yakovenko.domain.validation.validator.LocalDateTimeValidator
import test.yakovenko.domain.validation.validator.StringFieldValidator
import java.util.UUID

sealed class Feedback(
    open val id: UUID,
    open val authorId: UUID,
    open val estimation: Estimation,
    open val description: String?,
    open val publishedAt: LocalDateTime,
) {
    init {
        validate {
            description must StringFieldValidator("Description")
            publishedAt must LocalDateTimeValidator("PublishedAt", TimeZone.UTC)
        }
    }

    data class OperatorFeedback(
        override val id: UUID = UUID.randomUUID(),
        override val authorId: UUID,
        override val estimation: Estimation,
        override val description: String?,
        override val publishedAt: LocalDateTime,
        val operatorId: UUID
    ) : Feedback(
        id = id,
        authorId = authorId,
        estimation = estimation,
        description = description,
        publishedAt = publishedAt
    )

    data class ServiceFeedback(
        override val id: UUID = UUID.randomUUID(),
        override val authorId: UUID,
        override val estimation: Estimation,
        override val description: String?,
        override val publishedAt: LocalDateTime,
        val serviceId: UUID
    ) : Feedback(
        id = id,
        authorId = authorId,
        estimation = estimation,
        description = description,
        publishedAt = publishedAt
    )

    data class TariffFeedback(
        override val id: UUID = UUID.randomUUID(),
        override val authorId: UUID,
        override val estimation: Estimation,
        override val description: String?,
        override val publishedAt: LocalDateTime,
        val tariffId: UUID
    ) : Feedback(
        id = id,
        authorId = authorId,
        estimation = estimation,
        description = description,
        publishedAt = publishedAt
    )
}
