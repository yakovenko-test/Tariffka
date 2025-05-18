package test.yakovenko.domain.model

import test.yakovenko.domain.model.type.AverageEstimation
import test.yakovenko.domain.model.type.PaymentPlan
import java.net.URI
import java.net.URL
import java.util.UUID

internal object TestService {
    fun create(
        id: UUID = UUID.randomUUID(),
        operatorId: UUID,
        name: String = "TestService",
        description: String = "Test service description",
        url: URL = URI("https://service.test.com").toURL(),
        paymentPlan: PaymentPlan = PaymentPlan.Free,
        averageEstimation: AverageEstimation? = null
    ) = Service(
        id = id,
        operatorId = operatorId,
        name = name,
        description = description,
        url = url,
        paymentPlan = paymentPlan,
        averageEstimation = averageEstimation
    )
}