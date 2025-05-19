package usecase.discount

import repository.DiscountsRepository
import javax.inject.Inject

class ReadAllDiscountsUseCase @Inject constructor(
    private val discountsRepository: DiscountsRepository,
) {
    suspend operator fun invoke() = runCatching { discountsRepository.readAll() }
}