package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import code.yakovenko.tariffka.MOCKED_SUPPORT_TICKET

@Entity(tableName = "support_tickets")
data class SupportTicketEntity(
    @PrimaryKey val id: Long,
)

fun SupportTicketEntity.toDomain() = MOCKED_SUPPORT_TICKET
