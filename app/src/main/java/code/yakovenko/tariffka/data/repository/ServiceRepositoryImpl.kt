package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.ServiceDao
import code.yakovenko.tariffka.data.mapping.ServiceMapper
import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ServiceRepositoryImpl(
    private val serviceDao: ServiceDao
) : ServiceRepository {
    override suspend fun create(service: Service) {
        serviceDao.insertService(ServiceMapper.toData(service))
    }

    override fun readById(serviceId: Int): Flow<Service?> {
        return serviceDao.selectServiceById(serviceId).map { entity ->
            entity?.let { ServiceMapper.toDomain(entity) }
        }
    }

    override fun readByOperatorId(operatorId: Int): Flow<List<Service>> {
        return serviceDao.selectServicesByOperatorId(operatorId).map { entities ->
            entities.map { ServiceMapper.toDomain(it) }
        }
    }

    override fun readAll(): Flow<List<Service>> {
        return serviceDao.selectAllServices().map { entities ->
            entities.map { ServiceMapper.toDomain(it) }
        }
    }

    override suspend fun update(service: Service) {
        serviceDao.updateService(ServiceMapper.toData(service))
    }

    override suspend fun deleteById(optionId: Int) {
        serviceDao.deleteServiceById(optionId)
    }
}
