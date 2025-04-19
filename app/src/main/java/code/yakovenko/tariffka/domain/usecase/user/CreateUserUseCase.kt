package code.yakovenko.tariffka.domain.usecase.user

import code.yakovenko.tariffka.domain.exception.ModelDuplicateException
import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import code.yakovenko.tariffka.domain.repository.TariffRepository
import code.yakovenko.tariffka.domain.repository.UserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val operatorRepository: OperatorRepository,
    private val tariffRepository: TariffRepository
) {
    operator fun invoke(user: User): User {
        user.operatorId?.let { operatorId ->
            if (operatorRepository.notContainsId(operatorId)) {
                throw ModelNotFoundException("Operator", operatorId)
            }
        }

        user.tariffId?.let { tariffId ->
            if (tariffRepository.notContainsId(tariffId)) {
                throw ModelNotFoundException("Tariff", tariffId)
            }
        }

        if (userRepository.containsId(user.id)) {
            throw ModelDuplicateException("User", user.id)
        }

        return userRepository.create(user)
    }
}