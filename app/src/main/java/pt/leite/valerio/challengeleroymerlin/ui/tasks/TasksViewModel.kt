package pt.leite.valerio.challengeleroymerlin.ui.tasks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pt.leite.valerio.domain.entities.TaskEntity
import pt.leite.valerio.domain.useCases.tasks.DeleteTaskUseCase
import pt.leite.valerio.domain.useCases.tasks.GetTasksUseCase
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {
    private val taskListState = MutableLiveData<TaskListState>()
    private val deleteTaskState = MutableLiveData<DeleteTaskState>()

    fun taskListState() = taskListState
    fun deleteTaskState() = deleteTaskState

    fun fetchTasks() = viewModelScope.launch {
        taskListState.postValue(TaskListState.Loading)

        try {
            getTasksUseCase.call().also {
                taskListState.postValue(TaskListState.Success(it))
            }
        } catch (ex: Exception) {
            taskListState.postValue(TaskListState.Error(ex))
        }
    }

    fun deleteTask(task: TaskEntity) = viewModelScope.launch {
        deleteTaskState.postValue(DeleteTaskState.Loading)
        try {
            deleteTaskUseCase.call(task.id).also {
                deleteTaskState.postValue(DeleteTaskState.Success(task))
            }
        } catch (ex: Exception) {
            deleteTaskState.postValue(DeleteTaskState.Error(ex))
        }
    }

    sealed class TaskListState {
        object Loading: TaskListState()
        data class Success(val taskList: List<TaskEntity>): TaskListState()
        data class Error(val error: Throwable): TaskListState()
    }

    sealed class DeleteTaskState {
        object Loading: DeleteTaskState()
        data class Success(val task: TaskEntity): DeleteTaskState()
        data class Error(val error: Throwable): DeleteTaskState()
    }
}