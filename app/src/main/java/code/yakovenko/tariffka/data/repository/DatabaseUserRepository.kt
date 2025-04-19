package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.common.transformAll
import code.yakovenko.tariffka.data.mapping.user.UserEntityToDomainMapper
import code.yakovenko.tariffka.data.mapping.user.UserToEntityMapper
import code.yakovenko.tariffka.data.model.UserEntity
import code.yakovenko.tariffka.data.table.OperatorsTable
import code.yakovenko.tariffka.data.table.TariffsTable
import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.domain.repository.UserRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID
import javax.inject.Inject

class DatabaseUserRepository @Inject constructor(
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
