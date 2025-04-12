package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.core.enums.CountryCode
import code.yakovenko.tariffka.core.validation.StringFieldValidator
import code.yakovenko.tariffka.core.validation.URLValidator
import code.yakovenko.tariffka.core.validation.YearValidator
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Operator @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid = Uuid.random(),
    val name: String,
    val description: String?,
    val countryCode: CountryCode?,
    val url: String?,
    val yearOfFoundation: UInt?,
) {
    init {
        require(StringFieldValidator(name, "Name")) {
            StringFieldValidator.errorMessages.joinToString()
        }
        require(description?.let { StringFieldValidator(it, "Description") } != false) {
            StringFieldValidator.errorMessages.joinToString()
        }
        require(url?.let { URLValidator(it, "Url") } != false) {
            URLValidator.errorMessages.joinToString()
        }
        require(yearOfFoundation?.let { YearValidator(it, "YearOfFoundation") } != false) {
            YearValidator.errorMessages.joinToString()
        }
    }
}
