package test.yakovenko.domain.usecase.user.update

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelUpdateException
import test.yakovenko.domain.model.User.RegularUser
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.ServicesRepository
import test.yakovenko.domain.repository.TariffsRepository
import test.yakovenko.domain.repository.UsersRepository
import javax.inject.Inject

class UpdateRegularUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val operatorsRepository: OperatorsRepository,
    private val servicesRepository: ServicesRepository,
    private val tariffsRepository: TariffsRepository,
) {
    suspend operator fun invoke(user: RegularUser) = runCatching {
        if (!usersRepository.exists(user.id)) {
            throw ModelNotFoundException("User", user.id)
        }

        if (user.operatorId != null && !operatorsRepository.exists(user.operatorId)) {
            throw ModelNotFoundException("Operator", user.operatorId)
        }

        if (user.tariffId != null && !tariffsRepository.exists(user.tariffId)) {
            throw ModelNotFoundException("Tariff", user.tariffId)
        }

        for (id in user.serviceIds) {
            if (!servicesRepository.exists(id)) {
                throw ModelNotFoundException("Service", id)
            }
        }

        usersRepository.update(user) ?: throw ModelUpdateException("User", user.id)
    }
}