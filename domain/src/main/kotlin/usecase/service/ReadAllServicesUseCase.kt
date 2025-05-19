package usecase.service

import repository.ServicesRepository
import javax.inject.Inject

class ReadAllServicesUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
) {
    suspend operator fun invoke() = runCatching { servicesRepository.readAll() }
}