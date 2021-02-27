package com.eduramza.groovytdd.detail

import com.eduramza.groovytdd.BackendException
import com.eduramza.groovytdd.detail.model.Music
import com.eduramza.groovytdd.detail.service.DetailService
import com.eduramza.groovytdd.playlist.service.PlaylistAPI
import com.eduramza.groovytdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class DetailServiceTest: BaseUnitTest() {

    private val apiError = BackendException("Network Error")
    private val music: Music = mock()
    private val api = mock<PlaylistAPI>()
    private val fakeId = "1"

    @Test
    fun fetchMusicFromAPI() = runBlockingTest {
        val service = DetailService(api)

        service.fetchMusic(fakeId).first()

        verify(api).fetchMusicById(anyString())
    }

    @Test
    fun convertResponseToFlowAndEmits() = runBlockingTest {
        val service = mockSuccessfulCase()

        assertEquals(Result.success(music), service.fetchMusic(fakeId).first())
    }

    @Test
    fun propagueErrorsWhenBackendRequestFailure() = runBlockingTest {
        whenever(api.fetchMusicById(anyString())).thenThrow(apiError)
        val service = DetailService(api)

        assertEquals(apiError.message, service.fetchMusic(fakeId).first().exceptionOrNull()?.message)
    }

    private suspend fun mockSuccessfulCase(): DetailService {
        whenever(api.fetchMusicById(anyString())).thenReturn(music)

        return DetailService(api)
    }
}