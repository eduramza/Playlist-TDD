package com.eduramza.groovytdd.playlist.service

import com.eduramza.groovytdd.playlist.mapper.PlaylistRaw
import retrofit2.http.GET

interface PlaylistAPI {

    @GET("playlists")
    suspend fun fetchAllPlaylists() : List<PlaylistRaw>

}
