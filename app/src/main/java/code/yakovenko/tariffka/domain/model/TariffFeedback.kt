package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.core.validation.EstimationValidator
import code.yakovenko.tariffka.core.validation.LocalDateTimeValidator
import code.yakovenko.tariffka.core.validation.StringFieldValidator
import java.time.LocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class TariffFeedback @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid = Uuid.random(),
    val tariffId: Uuid,
    val userId: Uuid,
    val description: String?,
    val estimation: UByte,
    val publishedAt: LocalDateTime,
) {
    init {
        require(description?.let { StringFieldValidator(it, "Description") } != false) {
            StringFieldValidator.errorMessages.joinToString()
        }
        require(EstimationValidator(estimation, "Estimation")) {
            EstimationValidator.errorMessages.joinToString()
        }
        require(LocalDateTimeValidator(publishedAt, "PublishedAt")) {
            LocalDateTimeValidator.errorMessages.joinToString()
        }
    }
}
