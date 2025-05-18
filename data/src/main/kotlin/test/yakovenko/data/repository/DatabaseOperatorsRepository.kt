package test.yakovenko.data.repository

import org.jetbrains.exposed.sql.transactions.transaction
import test.yakovenko.common.transformAll
import test.yakovenko.data.mapping.operator.OperatorEntityToDomainMapper
import test.yakovenko.data.mapping.operator.OperatorToEntityMapper
import test.yakovenko.data.model.OperatorEntity
import test.yakovenko.domain.model.Operator
import test.yakovenko.domain.repository.OperatorsRepository
import java.util.UUID
import javax.inject.Inject

class DatabaseOperatorsRepository @Inject constructor(
    private val operatorToEntityMapper: OperatorToEntityMapper,
    private val operatorEntityToDomainMapper: OperatorEntityToDomainMapper
) : OperatorsRepository {
    override suspend fun create(operator: Operator) = transaction {
        operatorEntityToDomainMapper.transform(
            operatorToEntityMapper.transform(operator)
        )
    }

    override suspend fun read(operatorId: UUID) = transaction {
        OperatorEntity.findById(operatorId)?.let { operator ->
            operatorEntityToDomainMapper.transform(operator)
        }
    }

    override suspend fun update(operator: Operator) = transaction {
        OperatorEntity.findByIdAndUpdate(operator.id) {
            it.name = operator.name
            it.url = operator.url.toString()
            it.averageEstimation = operator.averageEstimation?.value
        }?.let { operatorEntity ->
            operatorEntityToDomainMapper.transform(operatorEntity)
        }
    }

    override suspend fun delete(operatorId: UUID): Boolean = transaction {
        OperatorEntity.findById(operatorId)?.delete()
    }

    override suspend fun readAll(): Collection<Operator> = transaction {
        operatorEntityToDomainMapper.transformAll(
            OperatorEntity.all().toList()
        )
    }

    override suspend fun exists(operatorId: UUID): Boolean = transaction {
        OperatorEntity.findById(operatorId) != null
    }
}
