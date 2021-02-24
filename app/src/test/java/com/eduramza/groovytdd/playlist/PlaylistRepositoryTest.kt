package com.eduramza.groovytdd.playlist

import com.eduramza.groovytdd.Playlist
import com.eduramza.groovytdd.playlist.repository.PlaylistRepository
import com.eduramza.groovytdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PlaylistRepositoryTest: BaseUnitTest() {

    private val service: PlaylistService = mock()
    private val playlists = mock<List<Playlist>>()


    @Test
    fun getPlaylistsFromService() = runBlockingTest {

        val repository = PlaylistRepository(service)

        repository.getPlaylists()

        verify(service).fetchPlaylists()
    }

    @Test
    fun emitPlaylistFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()
        assertEquals(playlists, repository.getPlaylists().first().getOrNull())
    }

    private suspend fun mockSuccessfulCase(): PlaylistRepository {
        whenever(service.fetchPlaylists()).thenReturn(
                flow {
                    emit(Result.success(playlists))
                }
        )

        val repository = PlaylistRepository(service)
        return repository
    }
}