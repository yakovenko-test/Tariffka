package usecase.user.update

import exception.ModelNotFoundException
import exception.ModelUpdateException
import model.User.OperatorDelegate
import repository.OperatorsRepository
import repository.UsersRepository
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