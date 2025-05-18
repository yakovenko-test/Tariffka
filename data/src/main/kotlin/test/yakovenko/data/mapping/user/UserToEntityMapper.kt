package test.yakovenko.data.mapping.user

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import test.yakovenko.tariffka.core.Mapper
import test.yakovenko.tariffka.data.model.UserEntity
import test.yakovenko.tariffka.data.table.OperatorsTable
import test.yakovenko.tariffka.data.table.TariffsTable
import test.yakovenko.tariffka.domain.model.User

class UserToEntityMapper : Mapper<User, UserEntity> {
    override fun transform(from: User): UserEntity = transaction {
        UserEntity.new(from.id) {
            operatorId = from.operatorId?.let { operatorId -> EntityID(operatorId, OperatorsTable) }
            tariffId = from.tariffId?.let { tariffId -> EntityID(tariffId, TariffsTable) }
            name = from.name
            surname = from.surname
            patronymic = from.patronymic
            username = from.username
            phoneNumber = from.phoneNumber
            email = from.email
            gender = from.gender
            birthDate = from.birthDate
            password = from.password
            role = from.role
        }
    }
}