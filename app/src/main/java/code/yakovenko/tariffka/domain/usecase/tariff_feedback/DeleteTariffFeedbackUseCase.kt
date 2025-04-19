package code.yakovenko.tariffka.domain.usecase.tariff_feedback

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import java.util.UUID
import javax.inject.Inject

class DeleteTariffFeedbackUseCase @Inject constructor(
    private val tariffFeedbackRepository: TariffFeedbackRepository
) {
    operator fun invoke(tariffFeedbackId: UUID) {
        if (tariffFeedbackRepository.notContainsId(tariffFeedbackId)) {
            throw ModelNotFoundException("TariffFeedback", tariffFeedbackId)
        }

        tariffFeedbackRepository.deleteById(tariffFeedbackId)
    }
}