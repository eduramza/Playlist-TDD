package com.eduramza.groovytdd.playlist.repository

import com.eduramza.groovytdd.Playlist
import com.eduramza.groovytdd.playlist.mapper.PlaylistMapper
import com.eduramza.groovytdd.playlist.service.PlaylistService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val service: PlaylistService,
    private val mapper: PlaylistMapper
) {

    suspend fun getPlaylists() : Flow<Result<List<Playlist>>> =
        service.fetchPlaylists().map {
            if(it.isSuccess)
                Result.success(mapper(it.getOrNull()!!))
            else
                Result.failure(it.exceptionOrNull()!!)
        }

}
