package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import kotlinx.coroutines.flow.Flow

class ReadTariffFeedbackByIdUseCase(
    private val tariffFeedbackRepository: TariffFeedbackRepository
) {
    operator fun invoke(tariffFeedbackId: Int): Flow<TariffFeedback?> {
        return tariffFeedbackRepository.readById(tariffFeedbackId)
    }
}