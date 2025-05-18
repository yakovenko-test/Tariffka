package test.yakovenko.domain.model

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import test.yakovenko.common.Relation
import test.yakovenko.domain.model.type.TicketStatus
import test.yakovenko.domain.validation.validate
import test.yakovenko.domain.validation.validator.LocalDateTimeValidator
import test.yakovenko.domain.validation.validator.PairRelationValidator
import test.yakovenko.domain.validation.validator.StringFieldValidator
import java.util.UUID

data class SupportTicket(
    val id: UUID = UUID.randomUUID(),
    val reporterId: UUID,
    val assigneeId: UUID?,
    val title: String,
    val description: String,
    val status: TicketStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    init {
        validate {
            title must StringFieldValidator("Title")
            description must StringFieldValidator("Description")
            createdAt must LocalDateTimeValidator("CreatedAt", TimeZone.UTC)
            updatedAt must LocalDateTimeValidator("UpdatedAr", TimeZone.UTC)

            (createdAt to updatedAt) must PairRelationValidator(
                "CreatedAt", "UpdatedAt", Relation.LE
            )
        }
    }
}
