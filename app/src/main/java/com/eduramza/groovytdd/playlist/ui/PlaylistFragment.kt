package com.eduramza.groovytdd.playlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.groovytdd.*
import com.eduramza.groovytdd.playlist.viewmodel.PlaylistViewModel
import com.eduramza.groovytdd.playlist.viewmodel.PlaylistViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlaylistViewModel
    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory
    private lateinit var progress: ProgressBar
    private lateinit var rvPlaylist: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        progress = view.findViewById(R.id.progress)
        rvPlaylist = view.findViewById(R.id.rv_playlist)

        setupViewModel()

        viewModel.loading.observe(this as LifecycleOwner, { loading ->
            when(loading){
                true -> progress.visibility = VISIBLE
                else -> progress.visibility = GONE
            }

        })

        viewModel.playlists.observe(this as LifecycleOwner, {playlist ->
            if (playlist.getOrNull() != null){
                setupList(rvPlaylist, playlist.getOrNull()!!)
            } else {
                //TODO
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
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance() = PlaylistFragment().apply {}
    }
}