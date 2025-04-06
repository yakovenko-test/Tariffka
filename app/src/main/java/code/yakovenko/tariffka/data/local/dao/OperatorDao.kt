package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import code.yakovenko.tariffka.data.local.entity.OperatorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OperatorDao {
    @Insert
    suspend fun insert(operatorEntity: OperatorEntity)

    @Query("SELECT * FROM operators WHERE id = :operatorId")
    suspend fun selectById(operatorId: Int): OperatorEntity?

    @Query("SELECT * FROM operators")
    fun selectAll(): Flow<List<OperatorEntity>>

    @Update
    suspend fun update(operatorEntity: OperatorEntity): Int

    @Query("DELETE FROM operators WHERE id = :operatorId")
    suspend fun deleteById(operatorId: Int): Int
}
