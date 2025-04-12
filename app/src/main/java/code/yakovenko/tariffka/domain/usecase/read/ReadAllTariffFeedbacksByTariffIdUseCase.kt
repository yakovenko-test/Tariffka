package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.exception.TariffNotFoundException
import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import code.yakovenko.tariffka.domain.repository.TariffRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReadAllTariffFeedbacksByTariffIdUseCase @Inject constructor(
    private val tariffFeedbackRepository: TariffFeedbackRepository,
    private val tariffRepository: TariffRepository
) {
    suspend operator fun invoke(tariffId: Uuid): Flow<List<TariffFeedback>> {
        if (tariffRepository.readById(tariffId).firstOrNull() == null) {
            throw throw TariffNotFoundException(tariffId)
        }

        return tariffFeedbackRepository.readByTariffId(tariffId)
    }
}
