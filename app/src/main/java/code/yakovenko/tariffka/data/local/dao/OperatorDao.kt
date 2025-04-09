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
    suspend fun insertOperator(operatorEntity: OperatorEntity)

    @Query("SELECT * FROM operators WHERE id = :operatorId")
    fun selectOperatorById(operatorId: Int): Flow<OperatorEntity?>

    @Query("SELECT * FROM operators")
    fun selectAllOperators(): Flow<List<OperatorEntity>>

    @Update
    suspend fun updateOperator(operatorEntity: OperatorEntity)

    @Query("DELETE FROM operators WHERE id = :operatorId")
    suspend fun deleteOperatorById(operatorId: Int)
}
