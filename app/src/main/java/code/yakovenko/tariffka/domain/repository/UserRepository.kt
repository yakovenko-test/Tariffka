package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.domain.model.utils.IdType

interface UserRepository {
    suspend fun create(user: User)

    suspend fun readById(userId: IdType): User?
    suspend fun readAll(): List<User>

    suspend fun update(user: User)

    suspend fun deleteById(userId: IdType)
}
