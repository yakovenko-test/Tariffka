package code.yakovenko.tariffka.di

import code.yakovenko.tariffka.domain.usecase.create.CreateDiscountUseCase
import code.yakovenko.tariffka.domain.usecase.create.CreateOperatorUseCase
import code.yakovenko.tariffka.domain.usecase.create.CreateServiceUseCase
import code.yakovenko.tariffka.domain.usecase.create.CreateSupportTicketUseCase
import code.yakovenko.tariffka.domain.usecase.create.CreateTariffFeedbackUseCase
import code.yakovenko.tariffka.domain.usecase.create.CreateTariffUseCase
import code.yakovenko.tariffka.domain.usecase.create.CreateUserUseCase
import code.yakovenko.tariffka.domain.usecase.delete.DeleteDiscountUseCase
import code.yakovenko.tariffka.domain.usecase.delete.DeleteOperatorUseCase
import code.yakovenko.tariffka.domain.usecase.delete.DeleteServiceUseCase
import code.yakovenko.tariffka.domain.usecase.delete.DeleteSupportTicketUseCase
import code.yakovenko.tariffka.domain.usecase.delete.DeleteTariffFeedbackUseCase
import code.yakovenko.tariffka.domain.usecase.delete.DeleteTariffUseCase
import code.yakovenko.tariffka.domain.usecase.delete.DeleteUserUseCase
import code.yakovenko.tariffka.domain.usecase.read.ReadAllDiscountsByOperatorIdUseCase
import code.yakovenko.tariffka.domain.usecase.read.ReadAllOperatorsUseCase
import code.yakovenko.tariffka.domain.usecase.read.ReadAllServicesByOperatorIdUseCase
import code.yakovenko.tariffka.domain.usecase.read.ReadAllServicesByUserIdUseCase
import code.yakovenko.tariffka.domain.usecase.read.ReadAllSupportTicketsByUserIdUseCase
import code.yakovenko.tariffka.domain.usecase.read.ReadAllTariffFeedbacksByTariffIdUseCase
import code.yakovenko.tariffka.domain.usecase.read.ReadAllTariffsByOperatorIdUseCase
import code.yakovenko.tariffka.domain.usecase.read.ReadDiscountByIdUseCase
import code.yakovenko.tariffka.domain.usecase.read.ReadOperatorByIdUseCase
import code.yakovenko.tariffka.domain.usecase.read.ReadServiceByIdUseCase
import code.yakovenko.tariffka.domain.usecase.read.ReadSupportTicketByIdUseCase
import code.yakovenko.tariffka.domain.usecase.read.ReadTariffByIdUseCase
import code.yakovenko.tariffka.domain.usecase.read.ReadTariffFeedbackByIdUseCase
import code.yakovenko.tariffka.domain.usecase.update.UpdateDiscountUseCase
import code.yakovenko.tariffka.domain.usecase.update.UpdateOperatorUseCase
import code.yakovenko.tariffka.domain.usecase.update.UpdateServiceUseCase
import code.yakovenko.tariffka.domain.usecase.update.UpdateSupportTicketUseCase
import code.yakovenko.tariffka.domain.usecase.update.UpdateTariffFeedbackUseCase
import code.yakovenko.tariffka.domain.usecase.update.UpdateTariffUseCase
import code.yakovenko.tariffka.domain.usecase.update.UpdateUserUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface UseCaseComponent {
    fun getCreateDiscountUseCase(): CreateDiscountUseCase
    fun getCreateOperatorUseCase(): CreateOperatorUseCase
    fun getCreateServiceUseCase(): CreateServiceUseCase
    fun getCreateSupportTicketUseCase(): CreateSupportTicketUseCase
    fun getCreateTariffFeedbackUseCase(): CreateTariffFeedbackUseCase
    fun getCreateTariffUseCase(): CreateTariffUseCase
    fun getCreateUserUseCase(): CreateUserUseCase

    fun getDeleteDiscountUseCase(): DeleteDiscountUseCase
    fun getDeleteOperatorUseCase(): DeleteOperatorUseCase
    fun getDeleteServiceUseCase(): DeleteServiceUseCase
    fun getDeleteSupportTicketUseCase(): DeleteSupportTicketUseCase
    fun getDeleteTariffFeedbackUseCase(): DeleteTariffFeedbackUseCase
    fun getDeleteTariffUseCase(): DeleteTariffUseCase
    fun getDeleteUserUseCase(): DeleteUserUseCase

    fun getReadAllDiscountsByOperatorIdUseCase(): ReadAllDiscountsByOperatorIdUseCase
    fun getReadAllOperatorsUseCase(): ReadAllOperatorsUseCase
    fun getReadAllServicesByOperatorIdUseCase(): ReadAllServicesByOperatorIdUseCase
    fun getReadAllServicesByUserIdUseCase(): ReadAllServicesByUserIdUseCase
    fun getReadAllSupportTicketsByUserIdUseCase(): ReadAllSupportTicketsByUserIdUseCase
    fun getReadAllTariffFeedbacksByTariffIdUseCase(): ReadAllTariffFeedbacksByTariffIdUseCase
    fun getReadAllTariffsByOperatorIdUseCase(): ReadAllTariffsByOperatorIdUseCase
    fun getReadDiscountByIdUseCase(): ReadDiscountByIdUseCase
    fun getReadOperatorByIdUseCase(): ReadOperatorByIdUseCase
    fun getReadServiceByIdUseCase(): ReadServiceByIdUseCase
    fun getReadSupportTicketByIdUseCase(): ReadSupportTicketByIdUseCase
    fun getReadTariffByIdUseCase(): ReadTariffByIdUseCase
    fun getReadTariffFeedbackByIdUseCase(): ReadTariffFeedbackByIdUseCase

    fun getUpdateDiscountUseCase(): UpdateDiscountUseCase
    fun getUpdateOperatorUseCase(): UpdateOperatorUseCase
    fun getUpdateServiceUseCase(): UpdateServiceUseCase
    fun getUpdateSupportTicketUseCase(): UpdateSupportTicketUseCase
    fun getUpdateTariffFeedbackUseCase(): UpdateTariffFeedbackUseCase
    fun getUpdateTariffUseCase(): UpdateTariffUseCase
    fun getUpdateUserUseCase(): UpdateUserUseCase
}
