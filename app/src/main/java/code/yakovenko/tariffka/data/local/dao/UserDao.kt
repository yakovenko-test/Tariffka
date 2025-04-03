package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import code.yakovenko.tariffka.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAll(): List<UserEntity>
}
