package com.example.dz2.ui.first.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dz2.data.TaskModel
import com.example.dz2.databinding.ItemListBinding

class TaskAdapter(val deleteClick:(TaskModel)->Unit):
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var list = mutableListOf<TaskModel>()

    fun addData(lists : List<TaskModel>){
        list.clear()
        list.addAll(lists)
        notifyDataSetChanged()
    }

    fun deleteData(lists: TaskModel){
        list.remove(lists)
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class TaskViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(taskModel: TaskModel) {
            binding.tvTitle.text = taskModel.title
            binding.tvDesc.text = taskModel.desc
            itemView.setOnLongClickListener {
                deleteClick(taskModel)
                false
            }
        }

    }
}