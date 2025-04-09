package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.UserDao
import code.yakovenko.tariffka.data.mapping.UserMapper
import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun create(user: User) {
        userDao.insertUser(UserMapper.toData(user))
    }

    override fun readById(userId: Int): Flow<User?> {
        return userDao.selectUserById(userId).map { entity ->
            entity?.let { UserMapper.toDomain(it) }
        }
    }

    override fun readAll(): Flow<List<User>> {
        return userDao.selectUserAll().map { entities ->
            entities.map { UserMapper.toDomain(it) }
        }
    }

    override suspend fun update(user: User) {
        userDao.updateUser(UserMapper.toData(user))
    }

    override suspend fun deleteById(userId: Int) {
        userDao.deleteUserById(userId)
    }
}
