package com.eduramza.groovytdd.detail.repository

import com.eduramza.groovytdd.detail.model.Music
import com.eduramza.groovytdd.playlist.service.PlaylistService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepository @Inject constructor(private val service: PlaylistService) {
    suspend fun getMusic() : Flow<Result<Music>>{
        return service.fetchMusic()
    }

}
