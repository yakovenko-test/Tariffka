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
    suspend fun insertTariff(tariffEntity: TariffEntity)

    @Query("SELECT * FROM tariffs WHERE id = :tariffId")
    fun selectTariffById(tariffId: Int): Flow<TariffEntity?>

    @Query("SELECT * FROM tariffs WHERE operator_id = :operatorId")
    fun selectTariffsByOperatorId(operatorId: Int): Flow<List<TariffEntity>>

    @Query("SELECT * FROM tariffs")
    fun selectAllTariffs(): Flow<List<TariffEntity>>

    @Update
    suspend fun updateTariff(tariffEntity: TariffEntity)

    @Query("DELETE FROM tariffs WHERE id = :tariffId")
    suspend fun deleteTariffById(tariffId: Int)
}
