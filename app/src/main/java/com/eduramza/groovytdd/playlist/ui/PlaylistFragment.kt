package com.eduramza.groovytdd.playlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.groovytdd.*
import com.eduramza.groovytdd.playlist.service.PlaylistService
import com.eduramza.groovytdd.playlist.repository.PlaylistRepository
import com.eduramza.groovytdd.playlist.service.PlaylistAPI
import com.eduramza.groovytdd.playlist.viewmodel.PlaylistViewModel
import com.eduramza.groovytdd.playlist.viewmodel.PlaylistViewModelFactory

class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlaylistViewModel
    lateinit var viewModelFactory: PlaylistViewModelFactory

    private val service = PlaylistService(object : PlaylistAPI{})
    private val repository = PlaylistRepository(service)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        setupViewModel()

        viewModel.playlists.observe(this as LifecycleOwner, {playlist ->
            if (playlist.getOrNull() != null){
                setupList(view, playlist.getOrNull()!!)
            }

        })
        return view
    }

    private fun setupList(
        view: View?,
        playlist: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)

            adapter = MyPlaylistRecyclerViewAdapter(playlist)
        }
    }

    private fun setupViewModel() {
        viewModelFactory = PlaylistViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance() = PlaylistFragment().apply {}
    }
}