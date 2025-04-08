package code.yakovenko.tariffka.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "tariff_feedbacks",
    foreignKeys = [
        ForeignKey(
            entity = TariffEntity::class,
            parentColumns = ["id"],
            childColumns = ["tariff_id"],
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
        )
    ],
    indices = [Index("tariff_id"), Index("user_id")]
)
data class TariffFeedbackEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("tariff_id")
    val tariffId: Int,
    @ColumnInfo("user_id")
    val userId: Int,
    val description: String?,
    val rating: Int,
    @ColumnInfo("published_at")
    val publishedAt: LocalDateTime,
)
