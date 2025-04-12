package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface UserRepository {
    suspend fun create(user: User)

    fun readById(userId: Uuid): Flow<User?>
    fun readAll(): Flow<List<User>>

    suspend fun update(user: User)

    suspend fun deleteById(userId: Uuid)
}
