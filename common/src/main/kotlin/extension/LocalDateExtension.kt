package extension

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

fun LocalDate.Companion.now(timeZone: TimeZone) = LocalDateTime.now(timeZone).date
