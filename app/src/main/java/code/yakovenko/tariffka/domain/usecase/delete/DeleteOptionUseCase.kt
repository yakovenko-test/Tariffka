package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.model.utils.IdType
import code.yakovenko.tariffka.domain.repository.OptionRepository
import javax.inject.Inject

class DeleteOptionUseCase @Inject constructor(
    private val optionRepository: OptionRepository
) {
    suspend operator fun invoke(optionId: IdType) {
        optionRepository.deleteById(optionId)
    }
}
