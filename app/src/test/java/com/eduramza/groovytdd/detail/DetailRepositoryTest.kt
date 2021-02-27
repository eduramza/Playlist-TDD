package com.eduramza.groovytdd.detail

import com.eduramza.groovytdd.detail.model.Music
import com.eduramza.groovytdd.detail.repository.DetailRepository
import com.eduramza.groovytdd.detail.service.DetailService
import com.eduramza.groovytdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class DetailRepositoryTest: BaseUnitTest() {

    private val successResponse = mock<Music>()
    private val service: DetailService = mock()
    private val fakeId = "1"
    private val exception = RuntimeException("Has an error with Backend call")

    @Test
    fun getPlaylistFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()

        repository.getMusic(anyString())

        verify(service).fetchMusic(anyString())

    }

    @Test
    fun emitFlowResultFromService() = runBlockingTest {

        val repository = mockSuccessfulCase()

        repository.getMusic(anyString())

        assertEquals(successResponse, repository.getMusic(fakeId).first().getOrNull())
    }

    @Test
    fun returnErroWithBackendRequestFailure() = runBlockingTest {
        whenever(service.fetchMusic(anyString())).thenReturn(
            flow {
                emit(Result.failure<Music>(exception))
            }
        )
        val repository = DetailRepository(service)

        assertEquals(exception, repository.getMusic(anyString()).first().exceptionOrNull())
    }

    private fun mockSuccessfulCase(): DetailRepository {
        runBlocking {
            whenever(service.fetchMusic(anyString())).thenReturn(
                flow {
                    emit(Result.success(successResponse))
                }
            )
        }

        return DetailRepository(service)
    }
}