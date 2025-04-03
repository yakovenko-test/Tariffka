package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import code.yakovenko.tariffka.data.local.entity.TariffEntity

@Dao
interface TariffDao {
    @Query("SELECT * FROM tariffs")
    suspend fun getAll(): List<TariffEntity>
}
