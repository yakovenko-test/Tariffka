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
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM users WHERE id = :userId")
    fun selectUserById(userId: Int): Flow<UserEntity?>

    @Query("SELECT * FROM users")
    fun selectUserAll(): Flow<List<UserEntity>>

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: Int)
}
