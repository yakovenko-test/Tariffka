package code.yakovenko.tariffka.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import code.yakovenko.tariffka.data.local.entity.SupportTicketEntity

@Dao
interface SupportTicketDao {
    @Query("SELECT * FROM support_tickets")
    suspend fun getAll(): List<SupportTicketEntity>
}
