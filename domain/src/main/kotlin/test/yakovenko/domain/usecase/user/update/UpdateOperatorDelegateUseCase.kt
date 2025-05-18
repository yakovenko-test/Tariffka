package test.yakovenko.domain.usecase.user.update

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelUpdateException
import test.yakovenko.domain.model.User.OperatorDelegate
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.UsersRepository
import javax.inject.Inject

class UpdateOperatorDelegateUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(user: OperatorDelegate) = runCatching {
        if (!usersRepository.exists(user.id)) {
            throw ModelNotFoundException("User", user.id)
        }

        if (!operatorsRepository.exists(user.operatorId)) {
            throw ModelNotFoundException("Operator", user.operatorId)
        }

        usersRepository.update(user) ?: throw ModelUpdateException("User", user.id)
    }
}