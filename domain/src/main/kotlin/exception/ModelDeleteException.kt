package exception

import java.util.*

class ModelDeleteException(modelName: String, modelId: UUID) :
    RuntimeException("Failed to delete $modelName with id $modelId")
