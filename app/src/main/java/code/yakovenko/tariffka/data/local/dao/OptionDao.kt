package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import code.yakovenko.tariffka.data.local.entity.OptionEntity

@Dao
interface OptionDao {
    @Query("SELECT * FROM options")
    suspend fun getAll(): List<OptionEntity>
}
