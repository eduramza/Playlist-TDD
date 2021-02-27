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
import org.mockito.ArgumentMatchers.anyString
import java.lang.RuntimeException

class DetailViewModelTest: BaseUnitTest() {

    private val detailRepository: DetailRepository = mock()
    private val mockMusic = mock<Music>()
    private val expected = Result.success(mockMusic)
    private val exception = RuntimeException("Something went wrong!")

    @Test
    fun getPlaylistDetailFromRepository() = runBlockingTest {
        val viewModel = mockSuccessDetails()

        viewModel.detail.getValueForTest()

        verify(detailRepository).getMusic(anyString())
    }

    @Test
    fun shouldEmittingLiveDataFromRepository() = runBlockingTest{

        val viewModel = mockSuccessDetails()

        assertEquals(expected, viewModel.detail.getValueForTest())
    }

    @Test
    fun emitErrorWhenFailure(){
        val viewModel = mockFailureResponse()

        assertEquals(exception, viewModel.detail.getValueForTest()?.exceptionOrNull())
    }

    private fun mockFailureResponse(): DetailViewModel {
        runBlocking {
            whenever(detailRepository.getMusic(anyString())).thenReturn(
                flow {
                    emit(Result.failure<Music>(exception))
                }
            )
        }

        return DetailViewModel(detailRepository)
    }

    private fun mockSuccessDetails(): DetailViewModel {
        runBlocking {
            whenever(detailRepository.getMusic(anyString())).thenReturn(flow {
                emit(expected)
            })
        }
        return DetailViewModel(detailRepository)
    }
}