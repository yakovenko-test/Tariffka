package code.yakovenko.domain.usecase.tariff_feedback

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.TariffFeedback
import code.yakovenko.domain.repository.TariffFeedbacksRepository
import java.util.UUID
import javax.inject.Inject

class ReadTariffFeedbackByIdUseCase @Inject constructor(
    private val tariffFeedbacksRepository: TariffFeedbacksRepository
) {
    operator fun invoke(tariffFeedbackId: UUID): TariffFeedback {
        return tariffFeedbacksRepository.readById(tariffFeedbackId)
            ?: throw ModelNotFoundException("TariffFeedback", tariffFeedbackId)
    }
}