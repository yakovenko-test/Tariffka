package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import kotlinx.coroutines.flow.Flow

class ReadAllTariffFeedbacksByTariffIdUseCase(
    private val tariffFeedbackRepository: TariffFeedbackRepository
) {
    operator fun invoke(tariffId: Int): Flow<List<TariffFeedback>> {
        return tariffFeedbackRepository.readByTariffId(tariffId)
    }
}
