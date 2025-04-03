package code.yakovenko.tariffka.data.local

import androidx.room.TypeConverter
import code.yakovenko.tariffka.domain.model.utils.TicketStatus

class Converters {
    @TypeConverter
    fun fromTicketStatus(ticketStatus: TicketStatus) = ticketStatus.name

    @TypeConverter
    fun toTicketStatus(ticketStatusString: String) = TicketStatus.valueOf(ticketStatusString)
}
