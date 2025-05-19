package usecase.user.update

import exception.ModelNotFoundException
import exception.ModelUpdateException
import model.User.RegularUser
import repository.OperatorsRepository
import repository.ServicesRepository
import repository.TariffsRepository
import repository.UsersRepository
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