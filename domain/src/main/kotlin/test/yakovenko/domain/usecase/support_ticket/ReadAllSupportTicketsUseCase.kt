package test.yakovenko.domain.usecase.support_ticket

import test.yakovenko.domain.repository.SupportTicketsRepository

class ReadAllSupportTicketsUseCase(
    private val supportTicketsRepository: SupportTicketsRepository,
) {
    suspend operator fun invoke() = runCatching { supportTicketsRepository.readAll() }
}