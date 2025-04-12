package code.yakovenko.tariffka.core.validation

interface Validator<T> {
    val errorMessages: List<String>

    operator fun invoke(value: T, fieldName: String): Boolean
}
