package model

import extension.now
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import java.net.URI
import java.net.URL
import java.util.*

internal object TestDiscount {
    fun create(
        id: UUID = UUID.randomUUID(),
        operatorId: UUID,
        title: String = "TestDiscount",
        description: String = "TestDiscount Description",
        url: URL = URI("https://discount.test.com").toURL(),
        activeFrom: LocalDate = LocalDate.now(TimeZone.UTC),
        activeUntil: LocalDate = LocalDate.now(TimeZone.UTC).plus(DatePeriod(months = 1))
    ) = Discount(
        id = id,
        operatorId = operatorId,
        title = title,
        description = description,
        url = url,
        activeFrom = activeFrom,
        activeUntil = activeUntil
    )
}