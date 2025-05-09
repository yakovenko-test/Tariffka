package code.yakovenko.data.mapping.support_ticket

import code.yakovenko.tariffka.core.Mapper
import code.yakovenko.tariffka.data.model.SupportTicketEntity
import code.yakovenko.tariffka.domain.model.SupportTicket

class SupportTicketEntityToDomainMapper : Mapper<SupportTicketEntity, SupportTicket> {
    override fun transform(from: SupportTicketEntity): SupportTicket {
        return SupportTicket(
            id = from.id.value,
            reporterId = from.reporterId.value,
            assigneeId = from.assigneeId?.value,
            title = from.title,
            description = from.description,
            createdAt = from.createdAt,
            updatedAt = from.updatedAt,
            status = from.status
        )
    }
}
