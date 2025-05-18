package test.yakovenko.domain.usecase.service

import test.yakovenko.domain.repository.ServicesRepository
import javax.inject.Inject

class ReadAllServicesUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
) {
    suspend operator fun invoke() = runCatching { servicesRepository.readAll() }
}