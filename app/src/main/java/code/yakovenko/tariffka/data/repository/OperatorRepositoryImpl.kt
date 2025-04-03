package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.OperatorDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import javax.inject.Inject

class OperatorRepositoryImpl @Inject constructor(
    private val operatorDao: OperatorDao
): OperatorRepository {
    override suspend fun getAll(): List<Operator> {
        return operatorDao.getAll().map { it.toDomain() }
    }
}
