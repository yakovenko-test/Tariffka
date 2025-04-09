package code.yakovenko.tariffka.mock

import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class ServiceRepositoryMock : ServiceRepository {
    private val data = mutableListOf<Service>()
    private val dataFlow = MutableStateFlow<List<Service>>(emptyList())

    override suspend fun create(service: Service) {
        data.add(service)
        dataFlow.value = data.toList()
    }

    override fun readById(serviceId: Int): Flow<Service?> {
        return dataFlow.map { services ->
            services.find { it.id == serviceId }
        }
    }

    override fun readByOperatorId(operatorId: Int): Flow<List<Service>> {
        return dataFlow.map { services ->
            services.filter { it.operatorId == operatorId }
        }
    }

    override fun readAll(): Flow<List<Service>> {
        return dataFlow.asStateFlow()
    }

    override suspend fun update(service: Service) {
        val index = data.indexOfFirst { it.id == service.id }

        if (index != -1) {
            data[index] = service
            dataFlow.value = data.toList()
        }
    }

    override suspend fun deleteById(serviceId: Int) {
        data.removeIf { it.id == serviceId }
        dataFlow.value = data
    }
}
