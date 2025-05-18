package test.yakovenko.domain.repository

import test.yakovenko.domain.model.User
import java.util.UUID

interface UsersRepository {
    suspend fun create(user: User): User?
    suspend fun read(userId: UUID): User?
    suspend fun update(user: User): User?
    suspend fun delete(userId: UUID): Boolean

    suspend fun readAll(): Collection<User>

    suspend fun exists(userId: UUID): Boolean
}
