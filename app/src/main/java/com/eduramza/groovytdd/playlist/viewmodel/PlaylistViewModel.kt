package com.eduramza.groovytdd.playlist.viewmodel

import androidx.lifecycle.*
import com.eduramza.groovytdd.Playlist
import com.eduramza.groovytdd.playlist.repository.PlaylistRepository
import kotlinx.coroutines.flow.onEach

class PlaylistViewModel(
        private val repository: PlaylistRepository
): ViewModel() {

    private val _playlists = liveData{
        loading.postValue(true)
        emitSource(repository.getPlaylists()
            .onEach { loading.postValue(false) }
            .asLiveData())
    }
    val playlists: LiveData<Result<List<Playlist>>>
        get() = _playlists

    val loading = MutableLiveData<Boolean>()

}
