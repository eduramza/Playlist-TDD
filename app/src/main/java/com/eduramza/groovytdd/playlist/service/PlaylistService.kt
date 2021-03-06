package com.eduramza.groovytdd.playlist.service

import com.eduramza.groovytdd.Playlist
import com.eduramza.groovytdd.detail.model.Music
import com.eduramza.groovytdd.playlist.mapper.PlaylistRaw
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject

class PlaylistService @Inject constructor(
    private val api: PlaylistAPI
) {

    suspend fun fetchPlaylists() : Flow<Result<List<PlaylistRaw>>> {
        return flow {
            emit(Result.success(api.fetchAllPlaylists()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
