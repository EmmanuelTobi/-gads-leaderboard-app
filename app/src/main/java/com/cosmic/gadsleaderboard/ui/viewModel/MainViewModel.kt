package com.cosmic.gadsleaderboard.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.cosmic.gadsleaderboard.apiservices.ApiHelper
import com.cosmic.gadsleaderboard.apiservices.submissionHelper
import com.cosmic.gadsleaderboard.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    fun getLeaderBoardByHours() = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))

        try {

            emit(Resource.success(data = apiHelper.getLeaderBoardByHours()))

        } catch (exception: Exception) {

            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))

        }
    }

    fun getLeaderBoardBySkillIQ() = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))

        try {

            emit(Resource.success(data = apiHelper.getLeaderBoardBySkillIQ()))

        } catch (exception: Exception) {

            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))

        }
    }

}

class SubmissionViewModel(private val submissionHelper: submissionHelper) : ViewModel() {

    fun makeSubmission() = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))

        try {

            emit(Resource.success(data = submissionHelper.makeSubmission()))

        } catch (exception: Exception) {

            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))

        }
    }

}