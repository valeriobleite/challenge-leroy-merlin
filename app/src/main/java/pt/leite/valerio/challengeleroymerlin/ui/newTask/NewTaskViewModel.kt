package pt.leite.valerio.challengeleroymerlin.ui.newTask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pt.leite.valerio.domain.entities.TaskEntity
import pt.leite.valerio.domain.useCases.tasks.InsertTaskUseCase
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(
    private val insertTaskUseCase: InsertTaskUseCase
) : ViewModel() {
    private val newTaskState = MutableLiveData<NewTaskState>()

    fun newTaskState() = newTaskState

    fun insertTask(taskEntity: TaskEntity) = viewModelScope.launch {
        newTaskState.postValue(NewTaskState.Loading)

        try {
            insertTaskUseCase.call(taskEntity).also {
                newTaskState.postValue(NewTaskState.Success)
            }
        } catch (ex: Exception) {
            newTaskState.postValue(NewTaskState.Error(ex))
        }
    }


    sealed class NewTaskState {
        object Loading: NewTaskState()
        object Success: NewTaskState()
        data class Error(val error: Throwable): NewTaskState()
    }
}