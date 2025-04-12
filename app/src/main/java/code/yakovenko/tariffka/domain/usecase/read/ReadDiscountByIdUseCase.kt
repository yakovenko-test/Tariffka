package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.exception.DiscountNotFoundException
import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.repository.DiscountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReadDiscountByIdUseCase @Inject constructor(
    private val discountRepository: DiscountRepository
) {
    suspend operator fun invoke(discountId: Uuid): Flow<Discount?> {
        if (discountRepository.readById(discountId).firstOrNull() == null) {
            throw throw DiscountNotFoundException(discountId)
        }

        return discountRepository.readById(discountId)
    }
}