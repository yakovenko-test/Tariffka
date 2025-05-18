package test.yakovenko.domain.usecase.discount

import test.yakovenko.domain.repository.DiscountsRepository
import javax.inject.Inject

class ReadAllDiscountsUseCase @Inject constructor(
    private val discountsRepository: DiscountsRepository,
) {
    suspend operator fun invoke() = runCatching { discountsRepository.readAll() }
}