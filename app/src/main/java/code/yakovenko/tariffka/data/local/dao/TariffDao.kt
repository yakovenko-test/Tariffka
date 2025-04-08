package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import code.yakovenko.tariffka.data.local.entity.TariffEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TariffDao {
    @Insert
    suspend fun insert(tariffEntity: TariffEntity)

    @Query("SELECT * FROM tariffs WHERE id = :tariffId")
    suspend fun selectById(tariffId: Int): TariffEntity?

    @Query("SELECT * FROM tariffs WHERE operator_id = :operatorId")
    fun selectByOperatorId(operatorId: Int): Flow<List<TariffEntity>>

    @Query("SELECT * FROM tariffs")
    fun selectAll(): Flow<List<TariffEntity>>

    @Update
    suspend fun update(tariffEntity: TariffEntity): Int

    @Query("DELETE FROM tariffs WHERE id = :tariffId")
    suspend fun deleteById(tariffId: Int): Int
}
