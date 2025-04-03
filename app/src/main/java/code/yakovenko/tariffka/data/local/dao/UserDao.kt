package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import code.yakovenko.tariffka.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(userEntity: UserEntity)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun selectById(userId: IdType): UserEntity?

    @Query("SELECT * FROM users")
    suspend fun selectAll(): Flow<List<UserEntity>>

    @Update
    suspend fun update(userEntity: UserEntity): Int

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteById(userId: IdType): Int
}
