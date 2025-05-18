package test.yakovenko.domain.model

import test.yakovenko.domain.model.type.AverageEstimation
import test.yakovenko.domain.model.type.GigabytePlan
import test.yakovenko.domain.model.type.MinutePlan
import test.yakovenko.domain.model.type.PaymentPlan
import test.yakovenko.domain.model.type.SmsPlan
import java.net.URI
import java.net.URL
import java.util.UUID

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