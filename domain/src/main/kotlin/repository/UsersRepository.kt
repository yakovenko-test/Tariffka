package repository

import model.User
import java.util.*

interface UsersRepository {
    suspend fun create(user: User): User?
    suspend fun read(userId: UUID): User?
    suspend fun update(user: User): User?
    suspend fun delete(userId: UUID): Boolean

    suspend fun readAll(): Collection<User>

    suspend fun exists(userId: UUID): Boolean
}
