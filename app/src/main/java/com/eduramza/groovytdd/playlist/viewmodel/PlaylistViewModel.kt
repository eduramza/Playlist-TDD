package com.eduramza.groovytdd.playlist.viewmodel

import androidx.lifecycle.*
import com.eduramza.groovytdd.Playlist
import com.eduramza.groovytdd.playlist.repository.PlaylistRepository

class PlaylistViewModel(
        private val repository: PlaylistRepository
): ViewModel() {

    private val _playlists = liveData{
        emitSource(repository.getPlaylists().asLiveData())
    }
    val playlists: LiveData<Result<List<Playlist>>>
        get() = _playlists

//    init {
//        viewModelScope.launch {
//            repository.getPlaylists()
//                    .collect {
//                        _playlists.value = it
//                    }
//        }
//    }
}
