package com.eduramza.groovytdd.playlist

import com.eduramza.groovytdd.Playlist
import kotlinx.coroutines.flow.Flow

class PlaylistService {

    suspend fun fetchPlaylists() : Flow<Result<List<Playlist>>> {
        TODO("Not yet implemented")
    }

}
