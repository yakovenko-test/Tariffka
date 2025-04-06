package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.UserDao
import code.yakovenko.tariffka.data.mapping.UserMapper
import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun create(user: User) {
        userDao.insert(UserMapper.toData(user))
    }

    override suspend fun readById(userId: Int): User? {
        return userDao.selectById(userId)?.let {
            UserMapper.toDomain(it)
        }
    }

    override suspend fun readAll(): Flow<List<User>> {
        return userDao.selectAll().map { entities ->
            entities.map { UserMapper.toDomain(it) }
        }
    }

    override suspend fun update(user: User) {
        userDao.update(UserMapper.toData(user))
    }

    override suspend fun deleteById(userId: Int) {
        userDao.deleteById(userId)
    }
}
