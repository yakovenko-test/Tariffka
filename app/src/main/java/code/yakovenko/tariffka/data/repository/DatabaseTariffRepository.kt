package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.common.transformAll
import code.yakovenko.tariffka.data.mapping.tariff.TariffEntityToDomainMapper
import code.yakovenko.tariffka.data.mapping.tariff.TariffToEntityMapper
import code.yakovenko.tariffka.data.model.TariffEntity
import code.yakovenko.tariffka.data.table.OperatorsTable
import code.yakovenko.tariffka.data.table.TariffsTable
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.TariffRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID
import javax.inject.Inject

class DatabaseTariffRepository @Inject constructor(
    private val tariffToEntityMapper: TariffToEntityMapper,
    private val tariffEntityToDomainMapper: TariffEntityToDomainMapper
) : TariffRepository {
    override fun create(tariff: Tariff): Tariff = transaction {
        tariffEntityToDomainMapper.transform(
            tariffToEntityMapper.transform(tariff)
        )
    }

    override fun readById(tariffId: UUID): Tariff? = transaction {
        TariffEntity.findById(tariffId)?.let { operator ->
            tariffEntityToDomainMapper.transform(operator)
        }
    }

    override fun readByOperatorId(operatorId: UUID): Collection<Tariff> = transaction {
        tariffEntityToDomainMapper.transformAll(
            TariffEntity.find { TariffsTable.operatorId eq operatorId }.toList()
        )
    }

    override fun readAll(): Collection<Tariff> = transaction {
        tariffEntityToDomainMapper.transformAll(
            TariffEntity.all().toList()
        )
    }

    override fun update(tariff: Tariff): Tariff? = transaction {
        TariffEntity.findByIdAndUpdate(tariff.id) {
            it.operatorId = EntityID(tariff.operatorId, OperatorsTable)
            it.name = tariff.name
            it.description = tariff.description
            it.cost = tariff.cost
            it.currency = tariff.currency
            it.minutesCount = tariff.minutesCount
            it.gigabytesCount = tariff.gigabytesCount
            it.averageEstimation = tariff.averageEstimation
        }?.let { tariffEntity ->
            tariffEntityToDomainMapper.transform(tariffEntity)
        }
    }

    override fun deleteById(tariffId: UUID): Unit = transaction {
        TariffEntity.findById(tariffId)?.delete()
    }

    override fun containsId(tariffId: UUID): Boolean = transaction {
        TariffEntity.findById(tariffId) != null
    }
}
