package code.yakovenko.tariffka.mock

import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class UserRepositoryMock : UserRepository {
    private val data = mutableListOf<User>()
    private val dataFlow = MutableStateFlow<List<User>>(emptyList())

    override suspend fun create(user: User) {
        data.add(user)
        dataFlow.value = data.toList()
    }

    override fun readById(userId: Int): Flow<User?> {
        return dataFlow.map { users ->
            users.find { it.id == userId }
        }
    }

    override fun readAll(): Flow<List<User>> {
        return dataFlow.asStateFlow()
    }

    override suspend fun update(user: User) {
        val index = data.indexOfFirst { it.id == user.id }

        if (index != -1) {
            data[index] = user
            dataFlow.value = data.toList()
        }
    }

    override suspend fun deleteById(userId: Int) {
        data.removeIf { it.id == userId }
        dataFlow.value = data
    }
}