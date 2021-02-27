package com.eduramza.groovytdd.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.eduramza.groovytdd.R
import com.eduramza.groovytdd.detail.model.Music
import com.eduramza.groovytdd.detail.viewmodel.DetailViewModel
import com.eduramza.groovytdd.detail.viewmodel.DetailViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    lateinit var viewModel: DetailViewModel
    @Inject
    lateinit var viewModelFactory: DetailViewModelFactory

    private lateinit var tvName: TextView
    private lateinit var tvDetails: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        setupViewModel()
        setupUi(view)

        viewModel.detail.observe(this as LifecycleOwner, {music ->
            if (music.isSuccess && music.getOrNull() != null){
                updateUi(music.getOrNull())
            } else {
                //response failure or are null
            }
        })
        return view
    }

    private fun setupUi(view: View) {
        tvName = view.findViewById(R.id.tv_detail_name)
        tvDetails = view.findViewById(R.id.tv_details_details)
    }

    private fun updateUi(music: Music?) {
        tvName.text = music?.name
        tvDetails.text = music?.details
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailFragment().apply { }
    }
}