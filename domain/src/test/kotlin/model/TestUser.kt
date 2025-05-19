package model

import kotlinx.datetime.LocalDate
import model.User.*
import model.type.Email
import model.type.Password
import model.type.PersonalInfo
import model.type.PhoneNumber
import java.util.*

internal object TestUser {
    fun createOperatorDelegate(
        id: UUID = UUID.randomUUID(),
        email: Email = Email("email@email.com"),
        password: Password = Password("Password123"),
        operatorId: UUID,
        personalInfo: PersonalInfo = PersonalInfo("Test", "User", null),
    ) = OperatorDelegate(
        id = id,
        email = email,
        password = password,
        operatorId = operatorId,
        personalInfo = personalInfo
    )

    fun createRegularUser(
        id: UUID = UUID.randomUUID(),
        email: Email = Email("email@email.com"),
        password: Password = Password("Password123"),
        operatorId: UUID? = null,
        tariffId: UUID? = null,
        serviceIds: Collection<UUID> = emptyList<UUID>(),
        personalInfo: PersonalInfo = PersonalInfo("Test", "User", null),
        phoneNumber: PhoneNumber = PhoneNumber("+1234567890"),
        birthDate: LocalDate = LocalDate(2000, 1, 1)
    ) = RegularUser(
        id = id,
        email = email,
        password = password,
        operatorId = operatorId,
        tariffId = tariffId,
        serviceIds = serviceIds,
        personalInfo = personalInfo,
        phoneNumber = phoneNumber,
        birthDate = birthDate
    )

    fun createSupportAgent(
        id: UUID = UUID.randomUUID(),
        email: Email = Email("email@email.com"),
        password: Password = Password("Password123"),
        personalInfo: PersonalInfo = PersonalInfo("Test", "User", null)
    ) = SupportAgent(
        id = id,
        email = email,
        password = password,
        personalInfo = personalInfo
    )
}