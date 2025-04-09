package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.repository.TariffRepository

class DeleteTariffUseCase(
    private val tariffRepository: TariffRepository
) {
    suspend operator fun invoke(tariffId: Int) {
        tariffRepository.deleteById(tariffId)
    }
}
