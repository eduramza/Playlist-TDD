package com.eduramza.groovytdd.playlist.repository

import com.eduramza.groovytdd.Playlist
import kotlinx.coroutines.flow.Flow

class PlaylistRepository {
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {
        TODO("Not yet implemented")
    }

}
