package com.eduramza.groovytdd.playlist.service

import com.eduramza.groovytdd.Playlist
import retrofit2.http.GET

interface PlaylistAPI {

    @GET("/playlists")
    fun fetchAllPlaylists() : List<Playlist>

}
