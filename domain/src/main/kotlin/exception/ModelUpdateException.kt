package exception

import java.util.*

class ModelUpdateException(modelName: String, modelId: UUID) :
    RuntimeException("Failed to update $modelName with id $modelId")
