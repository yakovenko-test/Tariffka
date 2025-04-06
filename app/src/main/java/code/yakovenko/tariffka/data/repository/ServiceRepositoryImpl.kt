package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.ServiceDao
import code.yakovenko.tariffka.data.mapping.ServiceMapper
import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(
    private val serviceDao: ServiceDao
) : ServiceRepository {
    override suspend fun create(service: Service) {
        serviceDao.insert(ServiceMapper.toData(service))
    }

    override suspend fun readById(optionId: Int): Service? {
        return serviceDao.selectById(optionId)?.let {
            ServiceMapper.toDomain(it)
        }
    }

    override suspend fun readAll(): Flow<List<Service>> {
        return serviceDao.selectAll().map { entities ->
            entities.map { ServiceMapper.toDomain(it) }
        }
    }

    override suspend fun update(service: Service) {
        serviceDao.update(ServiceMapper.toData(service))
    }

    override suspend fun deleteById(optionId: Int) {
        serviceDao.deleteById(optionId)
    }
}
