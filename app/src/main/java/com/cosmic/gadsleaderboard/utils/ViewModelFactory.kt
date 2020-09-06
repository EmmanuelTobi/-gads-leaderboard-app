package com.cosmic.gadsleaderboard.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cosmic.gadsleaderboard.apiservices.ApiHelper
import com.cosmic.gadsleaderboard.ui.viewModel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            return MainViewModel(apiHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}

