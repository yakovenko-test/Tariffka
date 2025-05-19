package model

import model.type.AverageEstimation
import java.net.URI
import java.net.URL
import java.util.*

internal object TestOperator {
    fun create(
        id: UUID = UUID.randomUUID(),
        name: String = "TestOperator",
        url: URL = URI("https://operator.test.com").toURL(),
        averageEstimation: AverageEstimation? = null,
    ) = Operator(
        id = id,
        name = name,
        url = url,
        averageEstimation = averageEstimation
    )
}
