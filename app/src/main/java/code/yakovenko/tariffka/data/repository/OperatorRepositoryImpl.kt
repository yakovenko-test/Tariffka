package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class OperatorRepositoryImpl @Inject constructor() : OperatorRepository {
    private val data = mutableListOf<Operator>()
    private val dataFlow = MutableStateFlow<List<Operator>>(emptyList())

    override suspend fun create(operator: Operator) {
        data.add(operator)
        dataFlow.value = data.toList()
    }

    override fun readById(operatorId: Uuid): Flow<Operator?> {
        return dataFlow.map { operators ->
            operators.find { it.id == operatorId }
        }
    }

    override fun readAll(): Flow<List<Operator>> {
        return dataFlow.asStateFlow()
    }

    override suspend fun update(operator: Operator) {
        val index = data.indexOfFirst { it.id == operator.id }

        if (index != -1) {
            data[index] = operator
            dataFlow.value = data.toList()
        }
    }

    override suspend fun deleteById(operatorId: Uuid) {
        data.removeIf { it.id == operatorId }
        dataFlow.value = data
    }
}
