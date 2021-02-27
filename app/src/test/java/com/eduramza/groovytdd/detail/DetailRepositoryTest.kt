package com.eduramza.groovytdd.detail

import com.eduramza.groovytdd.detail.repository.DetailRepository
import com.eduramza.groovytdd.playlist.service.PlaylistService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailRepositoryTest {

    lateinit var repository: DetailRepository
    private val service: PlaylistService = mock()

    @Test
    fun getPlaylistFromService() = runBlockingTest {
        repository = DetailRepository(service)

        repository.getMusic()

        verify(service).fetchMusic()

    }
}