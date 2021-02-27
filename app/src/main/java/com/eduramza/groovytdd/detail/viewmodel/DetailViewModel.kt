package com.eduramza.groovytdd.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduramza.groovytdd.detail.model.Music

class DetailViewModel: ViewModel() {

    val detail = MutableLiveData<Music>()
}