package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.User
import java.util.UUID

interface UserRepository {
    fun create(user: User): User

    fun readById(userId: UUID): User?
    fun readAll(): Collection<User>

    fun update(user: User): User?

    fun deleteById(userId: UUID)

    fun containsId(userId: UUID): Boolean
    fun notContainsId(userId: UUID) = !containsId(userId)
}
