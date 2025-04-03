package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import javax.inject.Inject

class DeleteTariffFeedbackUseCase @Inject constructor(
    private val tariffFeedbackRepository: TariffFeedbackRepository
) {
    suspend operator fun invoke(tariffFeedbackId: IdType) {
        tariffFeedbackRepository.deleteById(tariffFeedbackId)
    }
}
