package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import code.yakovenko.tariffka.data.local.entity.OptionEntity
import code.yakovenko.tariffka.domain.model.utils.IdType

@Dao
interface OptionDao {
    @Insert
    suspend fun insert(optionEntity: OptionEntity)

    @Query("SELECT * FROM options WHERE id = :optionId")
    suspend fun selectById(optionId: IdType): OptionEntity?

    @Query("SELECT * FROM options")
    suspend fun selectAll(): List<OptionEntity>

    @Update
    suspend fun update(optionEntity: OptionEntity): Int

    @Query("DELETE FROM options WHERE id = :optionId")
    suspend fun deleteById(optionId: IdType): Int
}
