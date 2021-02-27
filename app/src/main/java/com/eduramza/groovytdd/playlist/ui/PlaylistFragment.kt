package com.eduramza.groovytdd.playlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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
    private lateinit var progress: ProgressBar
    private lateinit var rvPlaylist: RecyclerView

    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        progress = view.findViewById(R.id.loader)
        rvPlaylist = view.findViewById(R.id.playlists_list)

        setupViewModel()

        viewModel.loader.observe(this as LifecycleOwner, { loading ->
            when(loading) {
                true -> progress.visibility = View.VISIBLE
                else -> progress.visibility = View.GONE
            }
        })

        viewModel.playlists.observe(this as LifecycleOwner, { playlists ->
            if(playlists.getOrNull() !=null)
                setupList(rvPlaylist, playlists.getOrNull()!!)
            else {
                //TODO
            }
        })

        return view
    }



    private fun setupList(
        view: View?,
        playlists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)

            adapter = MyPlaylistRecyclerViewAdapter(playlists) { id ->
                val action = PlaylistFragmentDirections.actionPlaylistFragmentToDetailFragment(id)

                findNavController().navigate(action)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {}
    }
}