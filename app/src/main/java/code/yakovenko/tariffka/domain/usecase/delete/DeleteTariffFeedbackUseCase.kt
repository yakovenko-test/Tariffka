package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository

class DeleteTariffFeedbackUseCase(
    private val tariffFeedbackRepository: TariffFeedbackRepository
) {
    suspend operator fun invoke(tariffFeedbackId: Int) {
        tariffFeedbackRepository.deleteById(tariffFeedbackId)
    }
}
