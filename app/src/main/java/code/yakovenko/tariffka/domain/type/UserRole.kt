package code.yakovenko.tariffka.domain.type

enum class UserRole {
    ADMIN, OPERATOR_SUPERVISOR, USER;

    companion object {
        val MAX_NAME_LENGTH = entries.maxOf { it.name.length }
    }
}
