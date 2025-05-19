package usecase.support_ticket

import repository.SupportTicketsRepository

class ReadAllSupportTicketsUseCase(
    private val supportTicketsRepository: SupportTicketsRepository,
) {
    suspend operator fun invoke() = runCatching { supportTicketsRepository.readAll() }
}