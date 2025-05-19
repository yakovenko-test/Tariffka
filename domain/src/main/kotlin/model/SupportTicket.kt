package model

import Relation
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import model.type.TicketStatus
import validation.validate
import validation.validator.LocalDateTimeValidator
import validation.validator.PairRelationValidator
import validation.validator.StringFieldValidator
import java.util.*

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
