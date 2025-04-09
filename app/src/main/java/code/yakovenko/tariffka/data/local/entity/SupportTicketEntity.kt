package code.yakovenko.tariffka.data.local.entity

import androidx.room.ColumnInfo
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
            childColumns = ["reporter_id"]
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["assignee_id"]
        )
    ],
    indices = [Index("reporter_id"), Index("assignee_id")]
)
data class SupportTicketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("reporter_id")
    val reporterId: Int,
    @ColumnInfo("assignee_id")
    val assigneeId: Int?,
    val title: String,
    val description: String,
    @ColumnInfo("created_at")
    val createdAt: LocalDateTime,
    @ColumnInfo("updated_at")
    val updatedAt: LocalDateTime,
    val status: TicketStatus,
)
