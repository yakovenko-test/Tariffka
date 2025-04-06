package code.yakovenko.tariffka.data.mapping

import code.yakovenko.tariffka.data.local.entity.SupportTicketEntity
import code.yakovenko.tariffka.domain.model.SupportTicket
import java.time.LocalDateTime

object SupportTicketMapper {
    fun toDomain(supportTicketEntity: SupportTicketEntity): SupportTicket {
        return SupportTicket(
            id = supportTicketEntity.id,
            reporterId = supportTicketEntity.reporterId,
            assigneeId = supportTicketEntity.assigneeId,
            title = supportTicketEntity.title,
            description = supportTicketEntity.description,
            createdAt = supportTicketEntity.createdAt,
            updatedAt = supportTicketEntity.updatedAt,
            status = supportTicketEntity.status
        ).apply {
            require(title.isNotBlank())
            require(description.isNotBlank())
            require(createdAt <= LocalDateTime.now())
            require(updatedAt <= LocalDateTime.now())
            require(createdAt <= updatedAt)
        }
    }

    fun toData(supportTicket: SupportTicket): SupportTicketEntity {
        return SupportTicketEntity(
            id = supportTicket.id,
            reporterId = supportTicket.reporterId,
            assigneeId = supportTicket.assigneeId,
            title = supportTicket.title,
            description = supportTicket.description,
            createdAt = supportTicket.createdAt,
            updatedAt = supportTicket.updatedAt,
            status = supportTicket.status
        )
    }
}
