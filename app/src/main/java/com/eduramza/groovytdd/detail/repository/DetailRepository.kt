package com.eduramza.groovytdd.detail.repository

import com.eduramza.groovytdd.detail.model.Music
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailRepository @Inject constructor() {
    suspend fun fetchMusic() : Flow<Result<Music>>{
        TODO("Not yet implemented")
    }

}
