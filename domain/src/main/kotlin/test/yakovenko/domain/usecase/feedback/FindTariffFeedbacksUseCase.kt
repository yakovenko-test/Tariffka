package test.yakovenko.domain.usecase.feedback

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.TariffsRepository
import java.util.UUID

class FindTariffFeedbacksUseCase(
    private val feedbacksRepository: FeedbacksRepository,
    private val tariffsRepository: TariffsRepository,
) {
    suspend operator fun invoke(tariffId: UUID) = runCatching {
        if (!tariffsRepository.exists(tariffId)) {
            throw ModelNotFoundException("Tariff", tariffId)
        }

        feedbacksRepository.findForTariff(tariffId)
    }
}