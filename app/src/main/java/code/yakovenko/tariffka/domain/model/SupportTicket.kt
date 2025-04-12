package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.core.enums.TicketStatus
import code.yakovenko.tariffka.core.validation.LocalDateTimeValidator
import code.yakovenko.tariffka.core.validation.StringFieldValidator
import java.time.LocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class SupportTicket @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid = Uuid.random(),
    val reporterId: Uuid,
    val assigneeId: Uuid?,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val status: TicketStatus,
) {
    init {
        require(StringFieldValidator(title, "Title")) {
            StringFieldValidator.errorMessages.joinToString()
        }
        require(StringFieldValidator(description, "Description")) {
            StringFieldValidator.errorMessages.joinToString()
        }
        require(LocalDateTimeValidator(createdAt, "CreatedAt")) {
            LocalDateTimeValidator.errorMessages.joinToString()
        }
        require(LocalDateTimeValidator(updatedAt, "UpdatedAt")) {
            LocalDateTimeValidator.errorMessages.joinToString()
        }
        require(LocalDateTimeValidator(createdAt, updatedAt, "CreatedAt", "UpdatedAt")) {
            LocalDateTimeValidator.errorMessages.joinToString()
        }
    }
}
