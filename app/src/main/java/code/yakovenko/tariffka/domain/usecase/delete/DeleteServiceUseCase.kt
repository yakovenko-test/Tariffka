package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.repository.ServiceRepository

class DeleteServiceUseCase(
    private val serviceRepository: ServiceRepository
) {
    suspend operator fun invoke(optionId: Int) {
        serviceRepository.deleteById(optionId)
    }
}
