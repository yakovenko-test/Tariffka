package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.OperatorDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.model.toData
import code.yakovenko.tariffka.domain.model.utils.IdType
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import javax.inject.Inject

class OperatorRepositoryImpl @Inject constructor(
    private val operatorDao: OperatorDao
) : OperatorRepository {
    override suspend fun create(operator: Operator) {
        operatorDao.insert(operator.toData())
    }

    override suspend fun readById(operatorId: IdType): Operator? {
        return operatorDao.selectById(operatorId)?.toDomain()
    }

    override suspend fun readAll(): List<Operator> {
        return operatorDao.selectAll().map { it.toDomain() }
    }

    override suspend fun update(operator: Operator) {
        operatorDao.update(operator.toData())
    }

    override suspend fun deleteById(operatorId: IdType) {
        deleteById(operatorId)
    }
}
