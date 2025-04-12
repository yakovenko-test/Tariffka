package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.exception.TariffNotFoundException
import code.yakovenko.tariffka.domain.exception.UserNotFoundException
import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import code.yakovenko.tariffka.domain.repository.TariffRepository
import code.yakovenko.tariffka.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class UpdateTariffFeedbackUseCase @Inject constructor(
    private val tariffFeedbackRepository: TariffFeedbackRepository,
    private val tariffRepository: TariffRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(tariffFeedback: TariffFeedback) {
        if (tariffRepository.readById(tariffFeedback.tariffId).firstOrNull() == null) {
            throw TariffNotFoundException(tariffFeedback.tariffId)
        }

        if (userRepository.readById(tariffFeedback.userId).firstOrNull() == null) {
            throw UserNotFoundException(tariffFeedback.userId)
        }

        tariffFeedbackRepository.update(tariffFeedback)
    }
}
