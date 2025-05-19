package usecase.feedback

import exception.ModelNotFoundException
import repository.FeedbacksRepository
import repository.TariffsRepository
import java.util.*

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