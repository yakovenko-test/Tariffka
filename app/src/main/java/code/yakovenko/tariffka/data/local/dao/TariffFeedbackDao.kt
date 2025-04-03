package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import code.yakovenko.tariffka.data.local.entity.TariffFeedbackEntity
import code.yakovenko.tariffka.domain.model.utils.IdType

@Dao
interface TariffFeedbackDao {
    @Insert
    suspend fun insert(tariffFeedbackEntity: TariffFeedbackEntity)

    @Query("SELECT * FROM tariff_feedbacks WHERE id = :tariffFeedbackId")
    suspend fun selectById(tariffFeedbackId: IdType): TariffFeedbackEntity?

    @Query("SELECT * FROM tariff_feedbacks")
    suspend fun selectAll(): List<TariffFeedbackEntity>

    @Update
    suspend fun update(tariffFeedbackEntity: TariffFeedbackEntity): Int

    @Query("DELETE FROM tariff_feedbacks WHERE id = :tariffFeedbackId")
    suspend fun deleteById(tariffFeedbackId: IdType): Int
}
