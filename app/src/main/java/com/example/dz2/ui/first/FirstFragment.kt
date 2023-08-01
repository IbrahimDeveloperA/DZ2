package com.example.dz2.ui.first

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.dz2.R
import com.example.dz2.core.BaseFragment
import com.example.dz2.data.TaskModel
import com.example.dz2.databinding.FragmentFirstBinding
import com.example.dz2.ui.first.adapter.TaskAdapter
import com.example.dz2.ui.first.viewmodel.FirstViewModel
import com.example.dz2.ui.second.SecondFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding>(R.layout.fragment_first) {

    private lateinit var adapter: TaskAdapter
    private lateinit var viewModel:FirstViewModel

    override fun inflateViewBinding(): FragmentFirstBinding {
        return FragmentFirstBinding.inflate(layoutInflater)
    }

    override fun initView() {
        adapter = TaskAdapter(this::deleteClick)
        binding.recyclerView.adapter = adapter
        viewModel = ViewModelProvider(requireActivity())[FirstViewModel::class.java]
        setData()
        binding.btnFab.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, SecondFragment()).commit()
        }
    }

    private fun setData() {
        val list = viewModel.getData()
        adapter.addData(list)
    }

    private fun deleteClick(taskModel: TaskModel) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete?")
        builder.setMessage("Delete sure")
        builder.setPositiveButton("Delete") { d, _ ->
            viewModel.deleteData(taskModel)
            setData()
            d.dismiss()
        }
        builder.setNegativeButton("No") { d, _ ->
            d.dismiss()
        }
        builder.create().show()
    }
}