package model

import model.type.*
import java.net.URI
import java.net.URL
import java.util.*

internal object TestTariff {
    fun create(
        id: UUID = UUID.randomUUID(),
        operatorId: UUID,
        name: String = "TestTariff",
        description: String = "Test tariff description",
        url: URL = URI("https://tariff.test.com").toURL(),
        minutePlan: MinutePlan = MinutePlan.Unlimited,
        gigabytePlan: GigabytePlan = GigabytePlan.Unlimited,
        smsPlan: SmsPlan = SmsPlan.Unlimited,
        paymentPlan: PaymentPlan = PaymentPlan.Free,
        averageEstimation: AverageEstimation? = null
    ) = Tariff(
        id = id,
        operatorId = operatorId,
        name = name,
        description = description,
        url = url,
        minutePlan = minutePlan,
        gigabytePlan = gigabytePlan,
        smsPlan = smsPlan,
        paymentPlan = paymentPlan,
        averageEstimation = averageEstimation
    )
}