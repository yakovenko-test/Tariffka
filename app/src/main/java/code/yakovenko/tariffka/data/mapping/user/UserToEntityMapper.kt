package code.yakovenko.tariffka.data.mapping.user

import code.yakovenko.tariffka.common.Mapper
import code.yakovenko.tariffka.data.model.UserEntity
import code.yakovenko.tariffka.data.table.OperatorsTable
import code.yakovenko.tariffka.data.table.TariffsTable
import code.yakovenko.tariffka.domain.model.User
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction

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