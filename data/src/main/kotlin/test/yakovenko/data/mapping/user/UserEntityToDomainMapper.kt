package test.yakovenko.data.mapping.user

import test.yakovenko.tariffka.core.Mapper
import test.yakovenko.tariffka.data.model.UserEntity
import test.yakovenko.tariffka.domain.model.User

class UserEntityToDomainMapper : Mapper<UserEntity, User> {
    override fun transform(from: UserEntity): User {
        return User(
            id = from.id.value,
            operatorId = from.operatorId?.value,
            tariffId = from.tariffId?.value,
            name = from.name,
            surname = from.surname,
            patronymic = from.patronymic,
            username = from.username,
            phoneNumber = from.phoneNumber,
            email = from.email,
            gender = from.gender,
            birthDate = from.birthDate,
            password = from.password,
            role = from.role
        )
    }
}
