package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import code.yakovenko.tariffka.data.local.entity.TariffFeedbackEntity

@Dao
interface TariffFeedbackDao {
    @Query("SELECT * FROM tariff_feedbacks")
    suspend fun getAll(): List<TariffFeedbackEntity>
}
