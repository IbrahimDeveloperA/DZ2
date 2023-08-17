package com.example.dz2.ui.second.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dz2.data.TaskModel
import com.example.dz2.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(private val repository: Repository) : ViewModel() {



    fun insertData(taskModel: TaskModel) {
        repository.insert(taskModel)
    }

}