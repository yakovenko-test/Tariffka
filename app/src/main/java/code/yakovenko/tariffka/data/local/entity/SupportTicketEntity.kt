package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import code.yakovenko.tariffka.core.utils.TicketStatus
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
    ],
    indices = [Index("reporterId"), Index("assigneeId")]
)
data class SupportTicketEntity(
    @PrimaryKey val id: Int,
    val reporterId: Int,
    val assigneeId: Int?,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val status: TicketStatus,
)
