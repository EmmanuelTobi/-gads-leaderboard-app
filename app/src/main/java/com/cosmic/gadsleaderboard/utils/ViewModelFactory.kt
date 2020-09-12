package com.cosmic.gadsleaderboard.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cosmic.gadsleaderboard.apiservices.ApiHelper
import com.cosmic.gadsleaderboard.apiservices.submissionHelper
import com.cosmic.gadsleaderboard.ui.viewModel.MainViewModel
import com.cosmic.gadsleaderboard.ui.viewModel.SubmissionViewModel

class ViewModelFactory(private val apiHelper: ApiHelper?, private val submissionHelper: submissionHelper?, val modelType: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelType == "submission") {

            if (modelClass.isAssignableFrom(SubmissionViewModel::class.java)) {

                return SubmissionViewModel(submissionHelper!!) as T
            }

        } else {

            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

                return MainViewModel(apiHelper!!) as T
            }

        }

        throw IllegalArgumentException("Unknown class name")
    }

}

