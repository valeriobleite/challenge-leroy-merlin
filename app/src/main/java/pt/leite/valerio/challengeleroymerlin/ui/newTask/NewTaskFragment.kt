package pt.leite.valerio.challengeleroymerlin.ui.newTask

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_new_task.*
import pt.leite.valerio.challengeleroymerlin.R
import pt.leite.valerio.domain.entities.TaskEntity

@AndroidEntryPoint
class NewTaskFragment : Fragment(R.layout.fragment_new_task) {
    private val viewModel: NewTaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabSave.setOnClickListener {
            viewModel.insertTask(TaskEntity(
                name = etName.text.toString(),
                description = etDescription.text.toString()
            ))
        }

        viewModel.newTaskState().observe(viewLifecycleOwner) { renderNewTask(it) }
    }

    private fun renderNewTask(state: NewTaskViewModel.NewTaskState) {
        when (state) {
            is NewTaskViewModel.NewTaskState.Loading -> {
                fabSave.isEnabled = false
            }

            is NewTaskViewModel.NewTaskState.Success -> {
                Snackbar.make(requireView(), R.string.new_task_success, Snackbar.LENGTH_LONG).show()
                findNavController().popBackStack()
            }

            is NewTaskViewModel.NewTaskState.Error -> {
                fabSave.isEnabled = true
                Snackbar.make(requireView(), R.string.new_task_error, Snackbar.LENGTH_LONG).show()
            }
        }
    }

}