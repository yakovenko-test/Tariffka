package code.yakovenko.tariffka.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import code.yakovenko.tariffka.data.local.dao.OperatorDao
import code.yakovenko.tariffka.data.local.entity.OperatorEntity
import code.yakovenko.tariffka.data.local.entity.OptionEntity
import code.yakovenko.tariffka.data.local.entity.SupportTicketEntity
import code.yakovenko.tariffka.data.local.entity.TariffDiscountEntity
import code.yakovenko.tariffka.data.local.entity.TariffEntity
import code.yakovenko.tariffka.data.local.entity.TariffFeedbackEntity
import code.yakovenko.tariffka.data.local.entity.UserEntity

@Database(
    entities = [
        OperatorEntity::class,
        OptionEntity::class,
        SupportTicketEntity::class,
        TariffDiscountEntity::class,
        TariffEntity::class,
        TariffFeedbackEntity::class,
        UserEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun operatorDao(): OperatorDao
    abstract fun optionDao(): OptionEntity
    abstract fun supportTicketDao(): SupportTicketEntity
    abstract fun tariffDiscountDao(): TariffDiscountEntity
    abstract fun tariffDao(): TariffEntity
    abstract fun tariffFeedbackEntityDao(): TariffFeedbackEntity
    abstract fun userDao(): UserEntity
}
