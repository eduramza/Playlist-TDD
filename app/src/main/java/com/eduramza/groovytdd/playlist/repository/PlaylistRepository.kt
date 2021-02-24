package com.eduramza.groovytdd.playlist.repository

import com.eduramza.groovytdd.playlist.service.PlaylistService

class PlaylistRepository(private val service: PlaylistService) {

    suspend fun getPlaylists() = service.fetchPlaylists()

}
