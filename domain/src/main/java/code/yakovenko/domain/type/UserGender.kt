package code.yakovenko.domain.type

enum class UserGender {
    FEMALE, MALE, NOT_SPECIFIED;

    companion object {
        val MAX_NAME_LENGTH = entries.maxOf { it.name.length }
    }
}
