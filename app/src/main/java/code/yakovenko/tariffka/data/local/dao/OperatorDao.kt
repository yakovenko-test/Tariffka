package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import code.yakovenko.tariffka.data.local.entity.OperatorEntity

@Dao
interface OperatorDao {
    @Query("SELECT * FROM operators")
    suspend fun getAll(): List<OperatorEntity>
}
