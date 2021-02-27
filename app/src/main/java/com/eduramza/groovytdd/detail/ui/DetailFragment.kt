package com.eduramza.groovytdd.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.eduramza.groovytdd.R
import com.eduramza.groovytdd.detail.viewmodel.DetailViewModel
import com.eduramza.groovytdd.detail.viewmodel.DetailViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    lateinit var viewModel: DetailViewModel
    @Inject
    lateinit var viewModelFactory: DetailViewModelFactory

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
        viewModel.detail.observe(this as LifecycleOwner, {music ->
            updateUi()
        })
        return view
    }

    private fun updateUi() {
        TODO("Not yet implemented")
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