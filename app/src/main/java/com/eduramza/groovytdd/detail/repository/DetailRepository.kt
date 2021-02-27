package com.eduramza.groovytdd.detail.repository

import com.eduramza.groovytdd.detail.model.Music
import com.eduramza.groovytdd.detail.service.DetailService
import com.eduramza.groovytdd.playlist.service.PlaylistService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepository @Inject constructor(private val service: DetailService) {
    suspend fun getMusic(id: String) : Flow<Result<Music>>{
        return service.fetchMusic(id)
    }

}
