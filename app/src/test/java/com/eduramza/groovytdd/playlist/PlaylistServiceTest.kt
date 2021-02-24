package com.eduramza.groovytdd.playlist

import com.eduramza.groovytdd.Playlist
import com.eduramza.groovytdd.playlist.service.PlaylistAPI
import com.eduramza.groovytdd.playlist.service.PlaylistService
import com.eduramza.groovytdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class PlaylistServiceTest: BaseUnitTest() {

    private lateinit var service: PlaylistService
    private val api: PlaylistAPI = mock()
    private val playlists: List<Playlist> = mock()

    @Test
    fun fetchPlaylistFromAPI() = runBlockingTest {
        service = PlaylistService(api)

        service.fetchPlaylists().first()

        verify(api).fetchAllPlaylists()
    }

    @Test
    fun convertValueAsFlowAndEmiteThem() = runBlockingTest {
        mockSuccessfulCase()

        assertEquals(Result.success(playlists), service.fetchPlaylists().first())
    }

    @Test
    fun emitErrorsResultWhenNetworkFailed() = runBlockingTest {
        mockFailureCase()

        assertEquals("Something went wrong",
                service.fetchPlaylists().first().exceptionOrNull()?.message)
    }

    private suspend fun mockFailureCase() {
        whenever(api.fetchAllPlaylists()).thenThrow(RuntimeException("From brackend Exception"))

        service = PlaylistService(api)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchAllPlaylists()).thenReturn(playlists)

        service = PlaylistService(api)
    }
}