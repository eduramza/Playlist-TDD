package com.eduramza.groovytdd.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eduramza.groovytdd.detail.repository.DetailRepository
import javax.inject.Inject

class DetailViewModelFactory @Inject constructor(
    private val repository: DetailRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(repository) as T
    }
}