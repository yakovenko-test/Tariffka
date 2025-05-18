package test.yakovenko.data.table

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date
import java.util.UUID

internal object UsersTable : IdTable<UUID>("users") {
    override val id = uuid("id").entityId()

    val operatorId = optReference("operator_id", OperatorsTable)
    val tariffId = optReference("tariff_id", OperatorsTable)
    val name = text("name")
    val surname = text("surname")
    val patronymic = text("patronymic").nullable()
    val username = text("username")
    val phoneNumber = text("phone_number")
    val email = text("email").nullable()
    val gender = enumerationByName<UserGender>("gender", UserGender.MAX_NAME_LENGTH)
    val birthDate = date("birth_date")
    val password = text("password")
    val role = enumerationByName<UserRole>("role", UserRole.MAX_NAME_LENGTH)
}
