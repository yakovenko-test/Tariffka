package test.yakovenko.domain.usecase.user.create

import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.model.User.OperatorDelegate
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.UsersRepository
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