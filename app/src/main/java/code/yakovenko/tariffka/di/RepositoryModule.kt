package code.yakovenko.tariffka.di

import code.yakovenko.tariffka.data.repository.DiscountRepositoryImpl
import code.yakovenko.tariffka.data.repository.OperatorRepositoryImpl
import code.yakovenko.tariffka.data.repository.ServiceRepositoryImpl
import code.yakovenko.tariffka.data.repository.SupportTicketRepositoryImpl
import code.yakovenko.tariffka.data.repository.TariffFeedbackRepositoryImpl
import code.yakovenko.tariffka.data.repository.TariffRepositoryImpl
import code.yakovenko.tariffka.data.repository.UserRepositoryImpl
import code.yakovenko.tariffka.domain.repository.DiscountRepository
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import code.yakovenko.tariffka.domain.repository.TariffRepository
import code.yakovenko.tariffka.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindDiscountRepository(impl: DiscountRepositoryImpl): DiscountRepository

    @Binds
    @Singleton
    fun bindOperatorRepository(impl: OperatorRepositoryImpl): OperatorRepository

    @Binds
    @Singleton
    fun bindServiceRepository(impl: ServiceRepositoryImpl): ServiceRepository

    @Binds
    @Singleton
    fun bindSupportTicketRepository(impl: SupportTicketRepositoryImpl): SupportTicketRepository

    @Binds
    @Singleton
    fun bindTariffFeedbackRepository(impl: TariffFeedbackRepositoryImpl): TariffFeedbackRepository

    @Binds
    @Singleton
    fun bindTariffRepository(impl: TariffRepositoryImpl): TariffRepository

    @Binds
    @Singleton
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}
