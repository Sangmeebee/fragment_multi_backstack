package com.sangmee.myapplication

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val currentFragment = MutableLiveData<Fragment>()

    val backStackTagList = mutableListOf<String>()
}
