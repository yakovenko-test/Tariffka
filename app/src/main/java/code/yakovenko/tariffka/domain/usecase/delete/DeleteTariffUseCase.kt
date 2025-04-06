package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.repository.TariffRepository
import javax.inject.Inject

class DeleteTariffUseCase @Inject constructor(
    private val tariffRepository: TariffRepository
) {
    suspend operator fun invoke(tariffId: Int) {
        tariffRepository.deleteById(tariffId)
    }
}
