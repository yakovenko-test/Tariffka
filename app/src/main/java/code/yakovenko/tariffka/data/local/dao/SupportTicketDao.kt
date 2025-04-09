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
    suspend fun insertSupportTicket(supportTicketEntity: SupportTicketEntity)

    @Query("SELECT * FROM support_tickets WHERE id = :supportTicketId")
    fun selectSupportTicketById(supportTicketId: Int): Flow<SupportTicketEntity?>

    @Query("SELECT * FROM support_tickets WHERE reporter_id = :userId")
    fun selectSupportTicketsByUserId(userId: Int): Flow<List<SupportTicketEntity>>

    @Query("SELECT * FROM support_tickets")
    fun selectAllSupportTickets(): Flow<List<SupportTicketEntity>>

    @Update
    suspend fun updateSupportTicket(supportTicketEntity: SupportTicketEntity)

    @Query("DELETE FROM support_tickets WHERE id = :supportTicketId")
    suspend fun deleteSupportTicketById(supportTicketId: Int)
}
