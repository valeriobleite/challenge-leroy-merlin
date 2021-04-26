package pt.leite.valerio.challengeleroymerlin.ui.tasks.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*
import pt.leite.valerio.challengeleroymerlin.ui.tasks.views.TaskItemView
import pt.leite.valerio.domain.entities.TaskEntity

class TasksAdapter(
    private val tasks: MutableList<TaskEntity> = mutableListOf(),
    private val listener: Listener? = null
): RecyclerView.Adapter<TasksAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskItemView(parent.context), listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(tasks[position])
    }

    override fun getItemCount() = tasks.count()

    fun setTasks(items: List<TaskEntity>) {
        tasks.clear()
        tasks.addAll(items)

        notifyDataSetChanged()
    }

    class ViewHolder(private val view: TaskItemView, private val listener: Listener? = null): RecyclerView.ViewHolder(view) {
        fun bindView(task: TaskEntity) {
            view.tvName.text = task.name
            view.tvDescription.text = task.description

            view.setOnLongClickListener {
                listener?.onLongClickItem(task)
                true
            }
        }
    }

    interface Listener {
        fun onLongClickItem(task: TaskEntity)
    }
}