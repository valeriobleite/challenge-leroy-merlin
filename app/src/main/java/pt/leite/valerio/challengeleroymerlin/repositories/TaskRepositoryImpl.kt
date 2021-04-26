package pt.leite.valerio.challengeleroymerlin.repositories

import pt.leite.valerio.challengeleroymerlin.mappers.toEntity
import pt.leite.valerio.challengeleroymerlin.mappers.toRoom
import pt.leite.valerio.data.local.dao.TaskDAO
import pt.leite.valerio.domain.entities.TaskEntity
import pt.leite.valerio.domain.repositories.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDAO: TaskDAO
): TaskRepository {
    override suspend fun getTasks(): List<TaskEntity> {
        return taskDAO.getAllActive().map { it.toEntity() }
    }

    override suspend fun insertTask(taskEntity: TaskEntity) {
        taskDAO.insert(taskEntity.toRoom())
    }

    override suspend fun deleteTask(id: Int) {
        taskDAO.makeInactive(id)
    }
}