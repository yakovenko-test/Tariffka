package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.OperatorDao
import code.yakovenko.tariffka.data.mapping.OperatorMapper
import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OperatorRepositoryImpl(
    private val operatorDao: OperatorDao
) : OperatorRepository {
    override suspend fun create(operator: Operator) {
        operatorDao.insertOperator(OperatorMapper.toData(operator))
    }

    override fun readById(operatorId: Int): Flow<Operator?> {
        return operatorDao.selectOperatorById(operatorId).map { entity ->
            entity?.let { OperatorMapper.toDomain(it) }
        }
    }

    override fun readAll(): Flow<List<Operator>> {
        return operatorDao.selectAllOperators().map { entities ->
            entities.map { OperatorMapper.toDomain(it) }
        }
    }

    override suspend fun update(operator: Operator) {
        operatorDao.updateOperator(OperatorMapper.toData(operator))
    }

    override suspend fun deleteById(operatorId: Int) {
        operatorDao.deleteOperatorById(operatorId)
    }
}
