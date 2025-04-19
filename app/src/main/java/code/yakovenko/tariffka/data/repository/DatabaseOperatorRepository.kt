package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.common.transformAll
import code.yakovenko.tariffka.data.mapping.operator.OperatorEntityToDomainMapper
import code.yakovenko.tariffka.data.mapping.operator.OperatorToEntityMapper
import code.yakovenko.tariffka.data.model.OperatorEntity
import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID
import javax.inject.Inject

class DatabaseOperatorRepository @Inject constructor(
    private val operatorToEntityMapper: OperatorToEntityMapper,
    private val operatorEntityToDomainMapper: OperatorEntityToDomainMapper
) : OperatorRepository {
    override fun create(operator: Operator): Operator = transaction {
        operatorEntityToDomainMapper.transform(
            operatorToEntityMapper.transform(operator)
        )
    }

    override fun readById(operatorId: UUID): Operator? = transaction {
        OperatorEntity.findById(operatorId)?.let { operator ->
            operatorEntityToDomainMapper.transform(operator)
        }
    }

    override fun readAll(): Collection<Operator> = transaction {
        operatorEntityToDomainMapper.transformAll(
            OperatorEntity.all().toList()
        )
    }

    override fun update(operator: Operator): Operator? = transaction {
        OperatorEntity.findByIdAndUpdate(operator.id) {
            it.name = operator.name
            it.countryCode = operator.countryCode
            it.url = operator.url
            it.yearOfFoundation = operator.yearOfFoundation
        }?.let { operatorEntity ->
            operatorEntityToDomainMapper.transform(operatorEntity)
        }
    }

    override fun deleteById(operatorId: UUID): Unit = transaction {
        OperatorEntity.findById(operatorId)?.delete()
    }

    override fun containsId(operatorId: UUID): Boolean = transaction {
        OperatorEntity.findById(operatorId) != null
    }
}
