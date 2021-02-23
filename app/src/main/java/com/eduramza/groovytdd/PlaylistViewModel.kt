package com.eduramza.groovytdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlaylistViewModel(
        private val repository: PlaylistRepository
): ViewModel() {

    private val _playlists = MutableLiveData<Result<List<Playlist>>>()
    val playlists: LiveData<Result<List<Playlist>>>
        get() = _playlists

    //val playlists = MutableLiveData<Result<List<Playlist>>>()

    init {
        viewModelScope.launch {
            repository.getPlaylists()
                    .collect {
                        _playlists.value = it
                    }
        }
    }
}
