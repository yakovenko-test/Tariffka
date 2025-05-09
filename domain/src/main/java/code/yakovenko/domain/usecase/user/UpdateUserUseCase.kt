package code.yakovenko.domain.usecase.user

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.User
import code.yakovenko.domain.repository.OperatorsRepository
import code.yakovenko.domain.repository.TariffsRepository
import code.yakovenko.domain.repository.UsersRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val operatorsRepository: OperatorsRepository,
    private val tariffsRepository: TariffsRepository
) {
    operator fun invoke(user: User): User? {
        user.operatorId?.let { operatorId ->
            if (operatorsRepository.notContainsId(operatorId)) {
                throw ModelNotFoundException("Operator", operatorId)
            }
        }

        user.tariffId?.let { tariffId ->
            if (tariffsRepository.notContainsId(tariffId)) {
                throw ModelNotFoundException("Tariff", tariffId)
            }
        }

        if (usersRepository.notContainsId(user.id)) {
            throw ModelNotFoundException("User", user.id)
        }

        return usersRepository.update(user)
    }
}