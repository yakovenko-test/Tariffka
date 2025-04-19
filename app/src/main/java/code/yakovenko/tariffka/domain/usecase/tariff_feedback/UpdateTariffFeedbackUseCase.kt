package code.yakovenko.tariffka.domain.usecase.tariff_feedback

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import code.yakovenko.tariffka.domain.repository.TariffRepository
import code.yakovenko.tariffka.domain.repository.UserRepository
import javax.inject.Inject

class UpdateTariffFeedbackUseCase @Inject constructor(
    private val tariffFeedbackRepository: TariffFeedbackRepository,
    private val tariffRepository: TariffRepository,
    private val userRepository: UserRepository
) {
    operator fun invoke(tariffFeedback: TariffFeedback): TariffFeedback? {
        if (tariffRepository.notContainsId(tariffFeedback.tariffId)) {
            throw ModelNotFoundException("Tariff", tariffFeedback.tariffId)
        }

        if (userRepository.notContainsId(tariffFeedback.authorId)) {
            throw ModelNotFoundException("User", tariffFeedback.authorId)
        }

        if (tariffFeedbackRepository.notContainsId(tariffFeedback.id)) {
            throw ModelNotFoundException("TariffFeedback", tariffFeedback.id)
        }

        return tariffFeedbackRepository.update(tariffFeedback)
    }
}