package code.yakovenko.tariffka.domain.exception

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class TariffFeedbackNotFoundException(tariffFeedbackId: Uuid) :
    RuntimeException("TariffFeedback with id $tariffFeedbackId not found")
