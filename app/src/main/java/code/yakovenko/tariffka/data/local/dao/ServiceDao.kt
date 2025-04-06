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
    suspend fun insert(serviceEntity: ServiceEntity)

    @Query("SELECT * FROM services WHERE id = :serviceId")
    suspend fun selectById(serviceId: Int): ServiceEntity?

    @Query("SELECT * FROM services")
    fun selectAll(): Flow<List<ServiceEntity>>

    @Update
    suspend fun update(serviceEntity: ServiceEntity): Int

    @Query("DELETE FROM services WHERE id = :serviceId")
    suspend fun deleteById(serviceId: Int): Int
}
