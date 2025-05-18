package test.yakovenko.data.mapping.support_ticket

import test.yakovenko.tariffka.core.Mapper
import test.yakovenko.tariffka.data.model.SupportTicketEntity
import test.yakovenko.tariffka.domain.model.SupportTicket

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
