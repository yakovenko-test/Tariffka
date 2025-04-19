package code.yakovenko.tariffka.common.extension

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

fun LocalDate.Companion.now(
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): LocalDate {
    return LocalDateTime.now(timeZone).date
}
