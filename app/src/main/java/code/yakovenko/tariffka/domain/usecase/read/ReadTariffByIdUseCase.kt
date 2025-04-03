package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.repository.TariffRepository
import javax.inject.Inject

class ReadTariffByIdUseCase @Inject constructor(
    private val tariffRepository: TariffRepository,
) {
    suspend operator fun invoke(tariffId: IdType) = tariffRepository.readById(tariffId)
}
