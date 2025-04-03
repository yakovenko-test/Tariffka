package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.model.Option
import code.yakovenko.tariffka.domain.repository.OptionRepository
import javax.inject.Inject

class UpdateOptionUseCase @Inject constructor(
    private val optionRepository: OptionRepository
) {
    suspend operator fun invoke(option: Option) {
        optionRepository.update(option)
    }
}
