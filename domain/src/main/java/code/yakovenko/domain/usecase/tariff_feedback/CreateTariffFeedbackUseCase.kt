package code.yakovenko.domain.usecase.tariff_feedback

import code.yakovenko.domain.exception.ModelDuplicateException
import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.TariffFeedback
import code.yakovenko.domain.repository.TariffFeedbacksRepository
import code.yakovenko.domain.repository.TariffsRepository
import code.yakovenko.domain.repository.UsersRepository
import javax.inject.Inject

class CreateTariffFeedbackUseCase @Inject constructor(
    private val tariffFeedbacksRepository: TariffFeedbacksRepository,
    private val tariffsRepository: TariffsRepository,
    private val usersRepository: UsersRepository
) {
    operator fun invoke(tariffFeedback: TariffFeedback): TariffFeedback {
        if (tariffsRepository.notContainsId(tariffFeedback.tariffId)) {
            throw ModelNotFoundException("Tariff", tariffFeedback.tariffId)
        }

        if (usersRepository.notContainsId(tariffFeedback.authorId)) {
            throw ModelNotFoundException("User", tariffFeedback.authorId)
        }

        if (tariffFeedbacksRepository.containsId(tariffFeedback.id)) {
            throw ModelDuplicateException("TariffFeedback", tariffFeedback.id)
        }

        return tariffFeedbacksRepository.create(tariffFeedback)
    }
}