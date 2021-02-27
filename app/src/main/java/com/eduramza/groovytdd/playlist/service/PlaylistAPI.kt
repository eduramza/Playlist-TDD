package com.eduramza.groovytdd.playlist.service

import com.eduramza.groovytdd.detail.model.Music
import com.eduramza.groovytdd.playlist.mapper.PlaylistRaw
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaylistAPI {

    @GET("playlists")
    suspend fun fetchAllPlaylists() : List<PlaylistRaw>

    @GET("playlists/{id}")
    suspend fun fetchMusicById(@Path("id") id: String): Music

}
