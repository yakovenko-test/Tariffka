package test.yakovenko.data.model

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import test.yakovenko.tariffka.data.table.UsersTable
import java.util.UUID

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    var operatorId by UsersTable.operatorId
    var tariffId by UsersTable.tariffId
    var name by UsersTable.name
    var surname by UsersTable.surname
    var patronymic by UsersTable.patronymic
    var username by UsersTable.username
    var phoneNumber by UsersTable.phoneNumber
    var email by UsersTable.email
    var gender by UsersTable.gender
    var birthDate by UsersTable.birthDate
    var password by UsersTable.password
    var role by UsersTable.role

    companion object : UUIDEntityClass<UserEntity>(UsersTable)
}
