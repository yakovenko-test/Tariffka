package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.domain.exception.OperatorNotFoundException
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import code.yakovenko.tariffka.domain.repository.TariffRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class CreateTariffUseCase @Inject constructor(
    private val tariffRepository: TariffRepository,
    private val operatorRepository: OperatorRepository
) {
    suspend operator fun invoke(tariff: Tariff) {
        if (operatorRepository.readById(tariff.operatorId).firstOrNull() == null) {
            throw OperatorNotFoundException(tariff.operatorId)
        }

        tariffRepository.create(tariff)
    }
}
