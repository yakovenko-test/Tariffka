package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.domain.exception.OperatorNotFoundException
import code.yakovenko.tariffka.domain.exception.TariffNotFoundException
import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import code.yakovenko.tariffka.domain.repository.TariffRepository
import code.yakovenko.tariffka.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val operatorRepository: OperatorRepository,
    private val tariffRepository: TariffRepository
) {
    suspend operator fun invoke(user: User) {
        user.operatorId?.let { operatorId ->
            if (operatorRepository.readById(operatorId).firstOrNull() == null) {
                throw OperatorNotFoundException(operatorId)
            }
        }

        user.tariffId?.let { tariffId ->
            if (tariffRepository.readById(tariffId).firstOrNull() == null) {
                throw TariffNotFoundException(tariffId)
            }
        }

        userRepository.create(user)
    }
}
