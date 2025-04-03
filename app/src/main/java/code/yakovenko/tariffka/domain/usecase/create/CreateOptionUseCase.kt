package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.domain.model.Option
import code.yakovenko.tariffka.domain.repository.OptionRepository
import javax.inject.Inject

class CreateOptionUseCase @Inject constructor(
    private val optionRepository: OptionRepository
) {
    suspend operator fun invoke(option: Option) {
        optionRepository.create(option)
    }
}
