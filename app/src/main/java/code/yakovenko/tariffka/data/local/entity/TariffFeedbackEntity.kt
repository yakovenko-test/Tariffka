package code.yakovenko.tariffka.data.local.entity

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
            childColumns = ["tariffId"],
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
        )
    ],
    indices = [Index("tariffId"), Index("userId")]
)
data class TariffFeedbackEntity(
    @PrimaryKey val id: Int,
    val tariffId: Int,
    val userId: Int,
    val description: String?,
    val rating: Int,
    val publishedAt: LocalDateTime,
)
