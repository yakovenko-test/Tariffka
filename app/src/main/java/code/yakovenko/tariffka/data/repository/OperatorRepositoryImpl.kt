package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.OperatorDao
import code.yakovenko.tariffka.data.mapping.OperatorMapper
import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OperatorRepositoryImpl @Inject constructor(
    private val operatorDao: OperatorDao
) : OperatorRepository {
    override suspend fun create(operator: Operator) {
        operatorDao.insert(OperatorMapper.toData(operator))
    }

    override suspend fun readById(operatorId: Int): Operator? {
        return operatorDao.selectById(operatorId)?.let {
            OperatorMapper.toDomain(it)
        }
    }

    override suspend fun readAll(): Flow<List<Operator>> {
        return operatorDao.selectAll().map { entities ->
            entities.map { OperatorMapper.toDomain(it) }
        }
    }

    override suspend fun update(operator: Operator) {
        operatorDao.update(OperatorMapper.toData(operator))
    }

    override suspend fun deleteById(operatorId: Int) {
        deleteById(operatorId)
    }
}
