package code.yakovenko.tariffka.data.mapping

import android.util.Patterns
import code.yakovenko.tariffka.data.local.entity.UserEntity
import code.yakovenko.tariffka.domain.model.User
import java.time.LocalDate

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
        ).apply {
            require(name.isNotBlank())
            require(surname.isNotBlank())
            require(patronymic?.isNotBlank() == true)
            require(login.isNotBlank())
            require(Patterns.EMAIL_ADDRESS.matcher(email).matches())
            require(Patterns.PHONE.matcher(phoneNumber).matches())
            require(birthDate <= LocalDate.now())
            require(password.isNotBlank())
        }
    }

    fun toData(user: User): UserEntity {
        return UserEntity(
            id = user.id,
            operatorId = user.operatorId,
            tariffId = user.tariffId,
            name = user.name.trim(),
            surname = user.surname.trim(),
            patronymic = user.patronymic?.trim(),
            login = user.login.trim(),
            phoneNumber = user.phoneNumber.trim(),
            email = user.email.trim(),
            gender = user.gender,
            role = user.role,
            birthDate = user.birthDate,
            password = user.password
        )
    }
}
