package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun create(user: User)

    fun readById(userId: Int): Flow<User?>
    fun readAll(): Flow<List<User>>

    suspend fun update(user: User)

    suspend fun deleteById(userId: Int)
}
