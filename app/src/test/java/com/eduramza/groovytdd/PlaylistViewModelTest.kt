package com.eduramza.groovytdd

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eduramza.groovytdd.utils.MainCoroutineScopeRule
import com.eduramza.groovytdd.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class PlaylistViewModelTest {

    @get:Rule
    val coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    val viewModel: PlaylistViewModel
    val repository: PlaylistRepository = mock()

    init {
        viewModel = PlaylistViewModel()
    }
    @Test
    fun getPlaylistFromRepository() {
        viewModel.playlists.getValueForTest()

        verify(repository).getPlaylists()
    }
}