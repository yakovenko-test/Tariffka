package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.exception.TariffNotFoundException
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.TariffRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReadTariffByIdUseCase @Inject constructor(
    private val tariffRepository: TariffRepository
) {
    suspend operator fun invoke(tariffId: Uuid): Flow<Tariff?> {
        if (tariffRepository.readById(tariffId).firstOrNull() == null) {
            throw TariffNotFoundException(tariffId)
        }

        return tariffRepository.readById(tariffId)
    }
}