package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import code.yakovenko.tariffka.data.local.entity.TariffFeedbackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TariffFeedbackDao {
    @Insert
    suspend fun insert(tariffFeedbackEntity: TariffFeedbackEntity)

    @Query("SELECT * FROM tariff_feedbacks WHERE id = :tariffFeedbackId")
    suspend fun selectById(tariffFeedbackId: Int): TariffFeedbackEntity?

    @Query("SELECT * FROM tariff_feedbacks")
    fun selectAll(): Flow<List<TariffFeedbackEntity>>

    @Update
    suspend fun update(tariffFeedbackEntity: TariffFeedbackEntity): Int

    @Query("DELETE FROM tariff_feedbacks WHERE id = :tariffFeedbackId")
    suspend fun deleteById(tariffFeedbackId: Int): Int
}
