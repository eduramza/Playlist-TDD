package com.eduramza.groovytdd.detail

import com.eduramza.groovytdd.detail.model.Music
import com.eduramza.groovytdd.detail.repository.DetailRepository
import com.eduramza.groovytdd.detail.viewmodel.DetailViewModel
import com.eduramza.groovytdd.utils.BaseUnitTest
import com.eduramza.groovytdd.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PlaylistViewModelTest: BaseUnitTest() {

    private val detailRepository: DetailRepository = mock()
    private val mockMusic = mock<Music>()
    private val expected = Result.success(mockMusic)

    @Test
    fun getPlaylistDetailFromRepository() = runBlockingTest {
        val viewModel = mockSuccessDetails()

        viewModel.detail.getValueForTest()

        verify(detailRepository).fetchMusic()
    }

    @Test
    fun shouldEmittingLiveDataFromRepository() = runBlockingTest{

        val viewModel = mockSuccessDetails()

        assertEquals(expected, viewModel.detail.getValueForTest())
    }

    private fun mockSuccessDetails(): DetailViewModel {
        runBlocking {
            whenever(detailRepository.fetchMusic()).thenReturn(flow {
                emit(expected)
            })
        }
        val viewModel = DetailViewModel(detailRepository)
        return viewModel
    }
}