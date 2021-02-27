package com.eduramza.groovytdd.detail.service

import android.util.Log
import com.eduramza.groovytdd.BackendException
import com.eduramza.groovytdd.detail.model.Music
import com.eduramza.groovytdd.playlist.service.PlaylistAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailService @Inject constructor(private val api: PlaylistAPI) {

    suspend fun fetchMusic(id: String) : Flow<Result<Music>> {
        return flow {
            emit(Result.success(api.fetchMusicById(id)))
        }.catch {
            emit(Result.failure(BackendException(it.message)))
        }
    }

}
