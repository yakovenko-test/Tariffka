package code.yakovenko.tariffka.domain.exception

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class SupportTicketNotFoundException(supportTicketId: Uuid) :
    RuntimeException("SupportTicket with id $supportTicketId not found")
