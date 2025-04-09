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
    suspend fun insertTariffFeedback(tariffFeedbackEntity: TariffFeedbackEntity)

    @Query("SELECT * FROM tariff_feedbacks WHERE id = :tariffFeedbackId")
    fun selectTariffFeedbackById(tariffFeedbackId: Int): Flow<TariffFeedbackEntity?>

    @Query("SELECT * FROM tariff_feedbacks WHERE tariff_id = :tariffId")
    fun selectTariffFeedbacksByTariffId(tariffId: Int): Flow<List<TariffFeedbackEntity>>

    @Query("SELECT * FROM tariff_feedbacks")
    fun selectAllTariffFeedbacks(): Flow<List<TariffFeedbackEntity>>

    @Update
    suspend fun updateTariffFeedback(tariffFeedbackEntity: TariffFeedbackEntity)

    @Query("DELETE FROM tariff_feedbacks WHERE id = :tariffFeedbackId")
    suspend fun deleteTariffFeedbackById(tariffFeedbackId: Int)
}
