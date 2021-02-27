package com.eduramza.groovytdd.detail

import com.eduramza.groovytdd.detail.repository.DetailRepository
import com.eduramza.groovytdd.detail.viewmodel.DetailViewModel
import com.eduramza.groovytdd.utils.BaseUnitTest
import com.eduramza.groovytdd.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class PlaylistViewModelTest: BaseUnitTest() {

    private lateinit var viewModel: DetailViewModel
    private val detailRepository: DetailRepository = mock()

    @Test
    fun getPlaylistDetailFromRepository(){
        viewModel = DetailViewModel()
        viewModel.detail.getValueForTest()

        verify(detailRepository).fetchMusic()
    }
}