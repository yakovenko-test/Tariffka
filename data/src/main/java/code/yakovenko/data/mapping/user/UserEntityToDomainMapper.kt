package code.yakovenko.data.mapping.user

import code.yakovenko.tariffka.core.Mapper
import code.yakovenko.tariffka.data.model.UserEntity
import code.yakovenko.tariffka.domain.model.User

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
