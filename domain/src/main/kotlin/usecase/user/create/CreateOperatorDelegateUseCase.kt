package usecase.user.create

import exception.ModelCreateException
import exception.ModelDuplicateException
import exception.ModelNotFoundException
import model.User.OperatorDelegate
import repository.OperatorsRepository
import repository.UsersRepository
import javax.inject.Inject

class CreateOperatorDelegateUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(user: OperatorDelegate) = runCatching {
        if (usersRepository.exists(user.id)) {
            throw ModelDuplicateException("User", user.id)
        }

        if (!operatorsRepository.exists(user.operatorId)) {
            throw ModelNotFoundException("Operator", user.operatorId)
        }

        usersRepository.create(user) ?: throw ModelCreateException("User", user.id)
    }
}