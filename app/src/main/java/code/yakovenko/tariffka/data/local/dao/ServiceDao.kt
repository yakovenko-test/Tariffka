package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import code.yakovenko.tariffka.data.local.entity.ServiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDao {
    @Insert
    suspend fun insertService(serviceEntity: ServiceEntity)

    @Query("SELECT * FROM services WHERE id = :serviceId")
    fun selectServiceById(serviceId: Int): Flow<ServiceEntity?>

    @Query("SELECT * FROM services WHERE operator_id = :operatorId")
    fun selectServicesByOperatorId(operatorId: Int): Flow<List<ServiceEntity>>

    @Query("SELECT * FROM services")
    fun selectAllServices(): Flow<List<ServiceEntity>>

    @Update
    suspend fun updateService(serviceEntity: ServiceEntity)

    @Query("DELETE FROM services WHERE id = :serviceId")
    suspend fun deleteServiceById(serviceId: Int)
}
