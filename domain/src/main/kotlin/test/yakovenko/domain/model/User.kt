package test.yakovenko.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import test.yakovenko.domain.model.type.Email
import test.yakovenko.domain.model.type.Password
import test.yakovenko.domain.model.type.PersonalInfo
import test.yakovenko.domain.model.type.PhoneNumber
import test.yakovenko.domain.validation.validate
import test.yakovenko.domain.validation.validator.LocaleDateValidator
import java.util.UUID

sealed class User(
    open val id: UUID,
    open val email: Email,
    open val password: Password,
) {
    data class Admin(
        override val id: UUID = UUID.randomUUID(),
        override val email: Email,
        override val password: Password,
    ) : User(
        id = id,
        email = email,
        password = password,
    )

    data class OperatorDelegate(
        override val id: UUID = UUID.randomUUID(),
        override val email: Email,
        override val password: Password,
        val operatorId: UUID,
        val personalInfo: PersonalInfo
    ) : User(
        id = id,
        email = email,
        password = password,
    )

    data class RegularUser(
        override val id: UUID = UUID.randomUUID(),
        override val email: Email,
        override val password: Password,
        val operatorId: UUID?,
        val tariffId: UUID?,
        val serviceIds: Collection<UUID>,
        val personalInfo: PersonalInfo,
        val phoneNumber: PhoneNumber,
        val birthDate: LocalDate,
    ) : User(
        id = id,
        email = email,
        password = password,
    ) {
        init {
            validate {
                birthDate must LocaleDateValidator("BirthDate", TimeZone.UTC)
            }
        }
    }

    data class SupportAgent(
        override val id: UUID = UUID.randomUUID(),
        override val email: Email,
        override val password: Password,
        val personalInfo: PersonalInfo,
    ) : User(
        id = id,
        email = email,
        password = password,
    )
}
