package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.core.validation.LocalDateTimeValidator
import code.yakovenko.tariffka.core.validation.StringFieldValidator
import java.time.LocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Discount(
    val id: Uuid = Uuid.random(),
    val operatorId: Uuid,
    val title: String,
    val description: String,
    val activeFrom: LocalDateTime,
    val activeUntil: LocalDateTime,
) {
    init {
        require(StringFieldValidator(title, "Title")) {
            StringFieldValidator.errorMessages.joinToString()
        }
        require(StringFieldValidator(description, "Description")) {
            StringFieldValidator.errorMessages.joinToString()
        }
        require(LocalDateTimeValidator(activeFrom, activeUntil, "ActiveFrom", "ActiveUntil")) {
            LocalDateTimeValidator.errorMessages.joinToString()
        }
    }
}
