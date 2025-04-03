package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import javax.inject.Inject

class CreateTariffFeedbackUseCase @Inject constructor(
    private val tariffFeedbackRepository: TariffFeedbackRepository
) {
    suspend operator fun invoke(tariffFeedback: TariffFeedback) {
        tariffFeedbackRepository.create(tariffFeedback)
    }
}
