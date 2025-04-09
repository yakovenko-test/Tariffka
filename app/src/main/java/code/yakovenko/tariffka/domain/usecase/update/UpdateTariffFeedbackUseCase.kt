package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository

class UpdateTariffFeedbackUseCase(
    private val tariffFeedbackRepository: TariffFeedbackRepository
) {
    suspend operator fun invoke(tariffFeedback: TariffFeedback) {
        tariffFeedbackRepository.update(tariffFeedback)
    }
}
