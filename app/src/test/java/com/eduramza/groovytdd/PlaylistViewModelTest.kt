package com.eduramza.groovytdd

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eduramza.groovytdd.utils.MainCoroutineScopeRule
import com.eduramza.groovytdd.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class PlaylistViewModelTest {

    @get:Rule
    val coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    //private val viewModel: PlaylistViewModel
    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)

    init {
        runBlockingTest {

        }


    }

    @Test
    fun getPlaylistFromRepository() = runBlockingTest {

        whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
        )

        val viewModel = PlaylistViewModel(repository)

        viewModel.playlists.getValueForTest()

        verify(repository).getPlaylists()
    }

    @Test
    fun emitsPlaylistFromRepository() = runBlockingTest {
        whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
        )

        val viewModel = PlaylistViewModel(repository)

        assertEquals(expected, viewModel.playlists.getValueForTest())
    }
}