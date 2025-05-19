package model

import extension.now
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import model.Feedback.*
import model.type.Estimation
import java.util.*

object TestFeedback {
    fun createOperatorFeedback(
        id: UUID = UUID.randomUUID(),
        authorId: UUID,
        estimation: Estimation = Estimation.lowest,
        description: String? = null,
        publishedAt: LocalDateTime = LocalDateTime.now(TimeZone.UTC),
        operatorId: UUID
    ): OperatorFeedback {
        return OperatorFeedback(
            id = id,
            authorId = authorId,
            estimation = estimation,
            description = description,
            publishedAt = publishedAt,
            operatorId = operatorId
        )
    }

    fun createServiceFeedback(
        id: UUID = UUID.randomUUID(),
        authorId: UUID,
        estimation: Estimation = Estimation.lowest,
        description: String? = null,
        publishedAt: LocalDateTime = LocalDateTime.now(TimeZone.UTC),
        serviceId: UUID
    ): ServiceFeedback {
        return ServiceFeedback(
            id = id,
            authorId = authorId,
            estimation = estimation,
            description = description,
            publishedAt = publishedAt,
            serviceId = serviceId
        )
    }

    fun createTariffFeedback(
        id: UUID = UUID.randomUUID(),
        authorId: UUID,
        estimation: Estimation = Estimation.lowest,
        description: String? = null,
        publishedAt: LocalDateTime = LocalDateTime.now(TimeZone.UTC),
        tariffId: UUID
    ): TariffFeedback {
        return TariffFeedback(
            id = id,
            authorId = authorId,
            estimation = estimation,
            description = description,
            publishedAt = publishedAt,
            tariffId = tariffId
        )
    }
}
