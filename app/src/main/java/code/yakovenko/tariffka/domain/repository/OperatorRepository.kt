package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Operator

interface OperatorRepository {
    suspend fun getAll(): List<Operator>
}
