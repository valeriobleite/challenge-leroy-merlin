package pt.leite.valerio.domain.useCases.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.leite.valerio.domain.entities.TaskEntity
import pt.leite.valerio.domain.repositories.TaskRepository

class InsertTaskUseCase(private val taskRepository: TaskRepository) {
    suspend fun call(task: TaskEntity) = withContext(Dispatchers.IO) {
        taskRepository.insertTask(task)
    }
}