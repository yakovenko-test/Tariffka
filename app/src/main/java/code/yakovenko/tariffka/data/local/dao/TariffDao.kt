package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import code.yakovenko.tariffka.data.local.entity.TariffEntity
import code.yakovenko.tariffka.domain.model.utils.IdType

@Dao
interface TariffDao {
    @Insert
    suspend fun insert(tariffEntity: TariffEntity)

    @Query("SELECT * FROM tariffs WHERE id = :tariffId")
    suspend fun selectById(tariffId: IdType): TariffEntity?

    @Query("SELECT * FROM tariffs")
    suspend fun selectAll(): List<TariffEntity>

    @Update
    suspend fun update(tariffEntity: TariffEntity): Int

    @Query("DELETE FROM tariffs WHERE id = :tariffId")
    suspend fun deleteById(tariffId: IdType): Int
}
