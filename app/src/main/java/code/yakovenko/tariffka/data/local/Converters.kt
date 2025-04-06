package code.yakovenko.tariffka.data.local

import androidx.room.TypeConverter
import code.yakovenko.tariffka.core.utils.TicketStatus
import code.yakovenko.tariffka.core.utils.UserGender
import code.yakovenko.tariffka.core.utils.UserRole
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromLocaleDateTime(localeDate: LocalDateTime): String {
        return localeDate.format(formatter)
    }

    @TypeConverter
    fun toLocaleDateTime(localeDateTimeString: String): LocalDateTime {
        return LocalDateTime.parse(localeDateTimeString, formatter)
    }

    @TypeConverter
    fun fromUserGender(userGender: UserGender): String {
        return userGender.name
    }

    @TypeConverter
    fun toUserGender(userGenderString: String): UserGender {
        return UserGender.valueOf(userGenderString)
    }

    @TypeConverter
    fun fromUserRole(userRole: UserRole): String {
        return userRole.name
    }

    @TypeConverter
    fun toUserRole(userRoleString: String): UserRole {
        return UserRole.valueOf(userRoleString)
    }

    @TypeConverter
    fun fromLocaleDate(localeDate: LocalDate): String {
        return localeDate.format(formatter)
    }

    @TypeConverter
    fun toLocaleDate(localeDateString: String): LocalDate {
        return LocalDate.parse(localeDateString, formatter)
    }

    @TypeConverter
    fun fromTicketStatus(ticketStatus: TicketStatus): String {
        return ticketStatus.name
    }

    @TypeConverter
    fun toTicketStatus(ticketStatusString: String): TicketStatus {
        return TicketStatus.valueOf(ticketStatusString)
    }
}
