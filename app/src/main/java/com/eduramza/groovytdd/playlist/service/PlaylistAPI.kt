package com.eduramza.groovytdd.playlist.service

import com.eduramza.groovytdd.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistAPI {

    suspend fun fetchAllPlaylists() : List<Playlist> {
        TODO("Not yet implemented")
    }

}
