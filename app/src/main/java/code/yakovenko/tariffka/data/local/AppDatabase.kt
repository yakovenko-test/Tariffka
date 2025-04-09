package code.yakovenko.tariffka.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import code.yakovenko.tariffka.data.local.dao.OperatorDao
import code.yakovenko.tariffka.data.local.dao.ServiceDao
import code.yakovenko.tariffka.data.local.dao.SupportTicketDao
import code.yakovenko.tariffka.data.local.dao.TariffDao
import code.yakovenko.tariffka.data.local.dao.TariffDiscountDao
import code.yakovenko.tariffka.data.local.dao.TariffFeedbackDao
import code.yakovenko.tariffka.data.local.dao.UserDao
import code.yakovenko.tariffka.data.local.entity.OperatorEntity
import code.yakovenko.tariffka.data.local.entity.ServiceEntity
import code.yakovenko.tariffka.data.local.entity.SupportTicketEntity
import code.yakovenko.tariffka.data.local.entity.TariffDiscountEntity
import code.yakovenko.tariffka.data.local.entity.TariffEntity
import code.yakovenko.tariffka.data.local.entity.TariffFeedbackEntity
import code.yakovenko.tariffka.data.local.entity.UserEntity

@Database(
    entities = [
        OperatorEntity::class,
        TariffEntity::class,
        TariffDiscountEntity::class,
        TariffFeedbackEntity::class,
        UserEntity::class,
        SupportTicketEntity::class,
        ServiceEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun operatorDao(): OperatorDao
    abstract fun tariffDao(): TariffDao
    abstract fun tariffDiscountDao(): TariffDiscountDao
    abstract fun tariffFeedbackEntityDao(): TariffFeedbackDao
    abstract fun userDao(): UserDao
    abstract fun supportTicketDao(): SupportTicketDao
    abstract fun serviceDao(): ServiceDao

    companion object {
        fun getDatabaseBuilder(ctx: Context): Builder<AppDatabase> {
            val appContext = ctx.applicationContext
            val dbFile = appContext.getDatabasePath("billing.db")

            return Room.databaseBuilder(
                context = appContext,
                klass = AppDatabase::class.java,
                name = dbFile.absolutePath
            )
        }
    }
}
