package code.yakovenko.tariffka.data.mapping

import code.yakovenko.tariffka.data.local.entity.UserEntity
import code.yakovenko.tariffka.domain.model.User

object UserMapper {
    fun toDomain(userEntity: UserEntity): User {
        return User(
            id = userEntity.id,
            operatorId = userEntity.operatorId,
            tariffId = userEntity.tariffId,
            name = userEntity.name,
            surname = userEntity.surname,
            patronymic = userEntity.patronymic,
            login = userEntity.login,
            phoneNumber = userEntity.phoneNumber,
            email = userEntity.email,
            gender = userEntity.gender,
            role = userEntity.role,
            birthDate = userEntity.birthDate,
            password = userEntity.password
        )
    }

    fun toData(user: User): UserEntity {
        return UserEntity(
            id = user.id,
            operatorId = user.operatorId,
            tariffId = user.tariffId,
            name = user.name,
            surname = user.surname,
            patronymic = user.patronymic,
            login = user.login.trim(),
            phoneNumber = user.phoneNumber,
            email = user.email,
            gender = user.gender,
            role = user.role,
            birthDate = user.birthDate,
            password = user.password
        )
    }
}
