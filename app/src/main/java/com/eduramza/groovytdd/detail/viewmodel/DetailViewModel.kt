package com.eduramza.groovytdd.detail.viewmodel

import androidx.lifecycle.*
import com.eduramza.groovytdd.detail.model.Music
import com.eduramza.groovytdd.detail.repository.DetailRepository

class DetailViewModel(
    private val repository: DetailRepository
    ): ViewModel() {

    private val _detail = liveData {
        emitSource(repository.getMusic("1").asLiveData())
    }

    val detail: LiveData<Result<Music>>
        get() = _detail

}