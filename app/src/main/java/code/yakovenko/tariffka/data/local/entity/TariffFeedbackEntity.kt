package code.yakovenko.tariffka.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import code.yakovenko.tariffka.MOCKED_TARIFF_FEEDBACK

@Entity(tableName = "tariff_feedbacks")
data class TariffFeedbackEntity(
    @PrimaryKey val id: Long,
)

fun TariffFeedbackEntity.toDomain() = MOCKED_TARIFF_FEEDBACK
