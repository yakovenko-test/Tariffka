package test.yakovenko.data.repository

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import test.yakovenko.tariffka.core.transformAll
import test.yakovenko.tariffka.data.mapping.user.UserEntityToDomainMapper
import test.yakovenko.tariffka.data.mapping.user.UserToEntityMapper
import test.yakovenko.tariffka.data.model.UserEntity
import test.yakovenko.tariffka.data.table.OperatorsTable
import test.yakovenko.tariffka.data.table.TariffsTable
import test.yakovenko.tariffka.domain.model.User
import test.yakovenko.tariffka.domain.repository.UserRepository
import java.util.UUID
import javax.inject.Inject

class DatabaseUsersRepository @Inject constructor(
    private val userToEntityMapper: UserToEntityMapper,
    private val userEntityToDomainMapper: UserEntityToDomainMapper
) : UserRepository {
    override fun create(user: User): User = transaction {
        userEntityToDomainMapper.transform(
            userToEntityMapper.transform(user)
        )
    }

    override fun readById(userId: UUID): User? = transaction {
        UserEntity.findById(userId)?.let { user ->
            userEntityToDomainMapper.transform(user)
        }
    }

    override fun readAll(): Collection<User> = transaction {
        userEntityToDomainMapper.transformAll(
            UserEntity.all().toList()
        )
    }

    override fun update(user: User): User? = transaction {
        UserEntity.findByIdAndUpdate(user.id) {
            it.operatorId = user.operatorId?.let { operatorId -> EntityID(operatorId, OperatorsTable) }
            it.tariffId = user.tariffId?.let { tariffId -> EntityID(tariffId, TariffsTable) }
            it.name = user.name
            it.surname = user.surname
            it.patronymic = user.patronymic
            it.username = user.username
            it.phoneNumber = user.phoneNumber
            it.email = user.email
            it.gender = user.gender
            it.birthDate = user.birthDate
            it.password = user.password
            it.role = user.role
        }?.let { user ->
            userEntityToDomainMapper.transform(user)
        }
    }

    override fun deleteById(userId: UUID): Unit = transaction {
        UserEntity.findById(userId)?.delete()
    }

    override fun containsId(userId: UUID): Boolean = transaction {
        UserEntity.findById(userId) != null
    }
}
