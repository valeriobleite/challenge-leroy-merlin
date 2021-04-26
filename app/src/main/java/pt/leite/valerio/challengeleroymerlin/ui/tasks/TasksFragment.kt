package pt.leite.valerio.challengeleroymerlin.ui.tasks

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tasks.*
import pt.leite.valerio.challengeleroymerlin.R
import pt.leite.valerio.challengeleroymerlin.extensions.navigateWithAnimations
import pt.leite.valerio.challengeleroymerlin.ui.tasks.adapter.TasksAdapter
import pt.leite.valerio.domain.entities.TaskEntity

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks), TasksAdapter.Listener {
    private val viewModel: TasksViewModel by viewModels()
    private val tasksAdapter by lazy { TasksAdapter(listener = this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTasks.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = tasksAdapter
        }

        fabNew.setOnClickListener {
            findNavController().navigateWithAnimations(TasksFragmentDirections.actionTasksFragmentToNewTaskFragment())
        }

        viewModel.taskListState().observe(viewLifecycleOwner) { renderTaskList(it) }
        viewModel.deleteTaskState().observe(viewLifecycleOwner) { renderDeleteTask(it) }
        viewModel.fetchTasks()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchTasks()
    }

    private fun renderTaskList(state: TasksViewModel.TaskListState) {
        when (state) {
            is TasksViewModel.TaskListState.Loading -> {
                pbTasks.isVisible = true
                rvTasks.isVisible = false
            }

            is TasksViewModel.TaskListState.Success -> {
                pbTasks.isVisible = false
                rvTasks.isVisible = true
                tasksAdapter.setTasks(state.taskList)
            }

            is TasksViewModel.TaskListState.Error -> {
                pbTasks.isVisible = false
                rvTasks.isVisible = false
                Snackbar.make(requireView(), R.string.tasks_error, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun renderDeleteTask(state: TasksViewModel.DeleteTaskState) {
        when (state) {
            is TasksViewModel.DeleteTaskState.Loading -> {
                fabNew.isVisible = false
            }

            is TasksViewModel.DeleteTaskState.Success -> {
                fabNew.isVisible = true
                viewModel.fetchTasks()
            }

            is TasksViewModel.DeleteTaskState.Error -> {
                fabNew.isVisible = true
                Snackbar.make(requireView(), R.string.delete_task_error, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onLongClickItem(task: TaskEntity) {
        viewModel.deleteTask(task)
    }
}