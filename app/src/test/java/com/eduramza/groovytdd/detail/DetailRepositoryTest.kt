package com.eduramza.groovytdd.detail

import com.eduramza.groovytdd.detail.model.Music
import com.eduramza.groovytdd.detail.repository.DetailRepository
import com.eduramza.groovytdd.playlist.service.PlaylistService
import com.eduramza.groovytdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class DetailRepositoryTest: BaseUnitTest() {

    private val successResponse = mock<Music>()
    private val service: PlaylistService = mock()
    val exception = RuntimeException("Has an error with Backend call")

    @Test
    fun getPlaylistFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()

        repository.getMusic()

        verify(service).fetchMusic()

    }

    @Test
    fun emitFlowResultFromService() = runBlockingTest {

        val repository = mockSuccessfulCase()

        repository.getMusic()

        assertEquals(successResponse, repository.getMusic().first().getOrNull())
    }

    @Test
    fun returnErroWithBackendRequestFailure() = runBlockingTest {
        whenever(service.fetchMusic()).thenReturn(
            flow {
                emit(Result.failure<Music>(exception))
            }
        )
        val repository = DetailRepository(service)

        assertEquals(exception, repository.getMusic().first().exceptionOrNull())
    }

    private fun mockSuccessfulCase(): DetailRepository {
        whenever(service.fetchMusic()).thenReturn(
            flow {
                emit(Result.success(successResponse))
            }
        )

        return DetailRepository(service)
    }
}