package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.User

interface UserRepository {
    suspend fun getAll(): List<User>
}
