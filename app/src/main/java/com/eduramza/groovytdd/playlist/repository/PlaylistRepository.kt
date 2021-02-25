package com.eduramza.groovytdd.playlist.repository

import com.eduramza.groovytdd.playlist.service.PlaylistService
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val service: PlaylistService) {

    suspend fun getPlaylists() = service.fetchPlaylists()

}
