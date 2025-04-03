package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import code.yakovenko.tariffka.MOCKED_SUPPORT_TICKET
import code.yakovenko.tariffka.domain.model.utils.TicketStatus
import java.time.LocalDateTime

@Entity(
    tableName = "support_tickets",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["reporterId"]
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["assigneeId"]
        )
    ]
)
data class SupportTicketEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val status: TicketStatus,
    val reporterId: Long,
    val assigneeId: Long?,
)

fun SupportTicketEntity.toDomain() = MOCKED_SUPPORT_TICKET
