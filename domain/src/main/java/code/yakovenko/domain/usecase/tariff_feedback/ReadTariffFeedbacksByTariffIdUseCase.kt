package code.yakovenko.domain.usecase.tariff_feedback

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.TariffFeedback
import code.yakovenko.domain.repository.TariffFeedbacksRepository
import code.yakovenko.domain.repository.TariffsRepository
import java.util.UUID
import javax.inject.Inject

class ReadTariffFeedbacksByTariffIdUseCase @Inject constructor(
    private val tariffFeedbacksRepository: TariffFeedbacksRepository,
    private val tariffsRepository: TariffsRepository
) {
    operator fun invoke(tariffId: UUID): Collection<TariffFeedback> {
        if (tariffsRepository.notContainsId(tariffId)) {
            throw throw ModelNotFoundException("Tariff", tariffId)
        }

        return tariffFeedbacksRepository.readByTariffId(tariffId)
    }
}