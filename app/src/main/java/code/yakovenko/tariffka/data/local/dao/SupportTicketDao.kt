package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import code.yakovenko.tariffka.data.local.entity.SupportTicketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SupportTicketDao {
    @Insert
    suspend fun insert(supportTicketEntity: SupportTicketEntity)

    @Query("SELECT * FROM support_tickets WHERE id = :supportTicketId")
    suspend fun selectById(supportTicketId: IdType): SupportTicketEntity?

    @Query("SELECT * FROM support_tickets")
    suspend fun selectAll(): Flow<List<SupportTicketEntity>>

    @Update
    suspend fun update(supportTicketEntity: SupportTicketEntity): Int

    @Query("DELETE FROM support_tickets WHERE id = :supportTicketId")
    suspend fun deleteById(supportTicketId: IdType): Int
}
