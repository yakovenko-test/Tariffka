package code.yakovenko.tariffka.domain.usecase.tariff_feedback

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import code.yakovenko.tariffka.domain.repository.TariffRepository
import java.util.UUID
import javax.inject.Inject

class ReadTariffFeedbacksByTariffIdUseCase @Inject constructor(
    private val tariffFeedbackRepository: TariffFeedbackRepository,
    private val tariffRepository: TariffRepository
) {
    operator fun invoke(tariffId: UUID): Collection<TariffFeedback> {
        if (tariffRepository.notContainsId(tariffId)) {
            throw throw ModelNotFoundException("Tariff", tariffId)
        }

        return tariffFeedbackRepository.readByTariffId(tariffId)
    }
}