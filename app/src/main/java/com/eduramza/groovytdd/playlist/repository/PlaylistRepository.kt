package com.eduramza.groovytdd.playlist.repository

import com.eduramza.groovytdd.playlist.PlaylistService

class PlaylistRepository(private val service: PlaylistService) {

    suspend fun getPlaylists() = service.fetchPlaylists()

}
