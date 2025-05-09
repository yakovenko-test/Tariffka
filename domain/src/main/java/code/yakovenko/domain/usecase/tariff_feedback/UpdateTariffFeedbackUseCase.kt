package code.yakovenko.domain.usecase.tariff_feedback

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.TariffFeedback
import code.yakovenko.domain.repository.TariffFeedbacksRepository
import code.yakovenko.domain.repository.TariffsRepository
import code.yakovenko.domain.repository.UsersRepository
import javax.inject.Inject

class UpdateTariffFeedbackUseCase @Inject constructor(
    private val tariffFeedbacksRepository: TariffFeedbacksRepository,
    private val tariffsRepository: TariffsRepository,
    private val usersRepository: UsersRepository
) {
    operator fun invoke(tariffFeedback: TariffFeedback): TariffFeedback? {
        if (tariffsRepository.notContainsId(tariffFeedback.tariffId)) {
            throw ModelNotFoundException("Tariff", tariffFeedback.tariffId)
        }

        if (usersRepository.notContainsId(tariffFeedback.authorId)) {
            throw ModelNotFoundException("User", tariffFeedback.authorId)
        }

        if (tariffFeedbacksRepository.notContainsId(tariffFeedback.id)) {
            throw ModelNotFoundException("TariffFeedback", tariffFeedback.id)
        }

        return tariffFeedbacksRepository.update(tariffFeedback)
    }
}