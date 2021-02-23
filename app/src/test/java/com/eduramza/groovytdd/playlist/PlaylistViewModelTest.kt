package com.eduramza.groovytdd.playlist

import com.eduramza.groovytdd.Playlist
import com.eduramza.groovytdd.playlist.repository.PlaylistRepository
import com.eduramza.groovytdd.playlist.viewmodel.PlaylistViewModel
import com.eduramza.groovytdd.utils.BaseUnitTest
import com.eduramza.groovytdd.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException

class PlaylistViewModelTest : BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Test Exception")

    @Test
    fun getPlaylistFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        viewModel.playlists.getValueForTest()

        verify(repository).getPlaylists()
    }

    @Test
    fun emitsPlaylistFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenRepositoryResponseAreFailure() = runBlockingTest {
        whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure<List<Playlist>>(exception))
                }
        )

        val viewModel = PlaylistViewModel(repository)
        assertEquals(exception, viewModel.playlists.getValueForTest()?.exceptionOrNull())
    }

    private fun mockSuccessfulCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                    flow {
                        emit(expected)
                    }
            )
        }

        return PlaylistViewModel(repository)
    }
}