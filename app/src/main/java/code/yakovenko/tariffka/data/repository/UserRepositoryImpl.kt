package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.UserDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.domain.model.toData
import code.yakovenko.tariffka.domain.model.utils.IdType
import code.yakovenko.tariffka.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun create(user: User) {
        userDao.insert(user.toData())
    }

    override suspend fun readById(userId: IdType): User? {
        return userDao.selectById(userId)?.toDomain()
    }

    override suspend fun readAll(): List<User> {
        return userDao.selectAll().map { it.toDomain() }
    }

    override suspend fun update(user: User) {
        userDao.update(user.toData())
    }

    override suspend fun deleteById(userId: IdType) {
        userDao.deleteById(userId)
    }
}
