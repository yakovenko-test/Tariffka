package code.yakovenko.core.validation

interface Validator<T> {
    val errorMessages: List<String>

    operator fun invoke(target: T, fieldName: String): Boolean
}
