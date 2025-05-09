package code.yakovenko.domain.model

import code.yakovenko.domain.type.UserRole
import kotlinx.datetime.LocalDate
import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val operatorId: UUID?,
    val tariffId: UUID?,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val birthDate: LocalDate,
    val password: String,
    val role: UserRole,
) {
    init {

    }
}
