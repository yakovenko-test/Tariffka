package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.UserDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): UserRepository {
    override suspend fun getAll(): List<User> {
        return userDao.getAll().map { it.toDomain() }
    }
}
