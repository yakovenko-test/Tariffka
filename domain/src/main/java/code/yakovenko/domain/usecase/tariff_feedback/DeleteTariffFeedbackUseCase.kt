package code.yakovenko.domain.usecase.tariff_feedback

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.repository.TariffFeedbacksRepository
import java.util.UUID
import javax.inject.Inject

class DeleteTariffFeedbackUseCase @Inject constructor(
    private val tariffFeedbacksRepository: TariffFeedbacksRepository
) {
    operator fun invoke(tariffFeedbackId: UUID) {
        if (tariffFeedbacksRepository.notContainsId(tariffFeedbackId)) {
            throw ModelNotFoundException("TariffFeedback", tariffFeedbackId)
        }

        tariffFeedbacksRepository.deleteById(tariffFeedbackId)
    }
}