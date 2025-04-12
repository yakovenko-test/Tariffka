package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.exception.TariffFeedbackNotFoundException
import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReadTariffFeedbackByIdUseCase @Inject constructor(
    private val tariffFeedbackRepository: TariffFeedbackRepository
) {
    suspend operator fun invoke(tariffFeedbackId: Uuid): Flow<TariffFeedback?> {
        if (tariffFeedbackRepository.readById(tariffFeedbackId).firstOrNull() == null) {
            throw TariffFeedbackNotFoundException(tariffFeedbackId)
        }

        return tariffFeedbackRepository.readById(tariffFeedbackId)
    }
}