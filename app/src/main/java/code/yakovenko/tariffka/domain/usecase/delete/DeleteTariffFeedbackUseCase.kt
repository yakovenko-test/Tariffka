package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.exception.TariffFeedbackNotFoundException
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DeleteTariffFeedbackUseCase @Inject constructor(
    private val tariffFeedbackRepository: TariffFeedbackRepository
) {
    suspend operator fun invoke(tariffFeedbackId: Uuid) {
        if (tariffFeedbackRepository.readById(tariffFeedbackId).firstOrNull() == null) {
            throw TariffFeedbackNotFoundException(tariffFeedbackId)
        }

        tariffFeedbackRepository.deleteById(tariffFeedbackId)
    }
}
