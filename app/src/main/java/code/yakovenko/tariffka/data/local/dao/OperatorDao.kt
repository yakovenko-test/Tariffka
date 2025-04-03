package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import code.yakovenko.tariffka.data.local.entity.OperatorEntity
import code.yakovenko.tariffka.domain.model.utils.IdType

@Dao
interface OperatorDao {
    @Insert
    suspend fun insert(operatorEntity: OperatorEntity)

    @Query("SELECT * FROM operators WHERE id = :operatorId")
    suspend fun selectById(operatorId: IdType): OperatorEntity?

    @Query("SELECT * FROM operators")
    suspend fun selectAll(): List<OperatorEntity>

    @Update
    suspend fun update(operatorEntity: OperatorEntity): Int

    @Query("DELETE FROM operators WHERE id = :operatorId")
    suspend fun deleteById(operatorId: IdType): Int
}
