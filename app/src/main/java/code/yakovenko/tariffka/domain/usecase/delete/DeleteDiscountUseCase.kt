package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.exception.DiscountNotFoundException
import code.yakovenko.tariffka.domain.repository.DiscountRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DeleteDiscountUseCase @Inject constructor(
    private val discountRepository: DiscountRepository
) {
    suspend operator fun invoke(discountId: Uuid) {
        if (discountRepository.readById(discountId).firstOrNull() == null) {
            throw DiscountNotFoundException(discountId)
        }

        discountRepository.deleteById(discountId)
    }
}
