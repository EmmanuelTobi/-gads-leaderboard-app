package com.cosmic.gadsleaderboard.ui

import ConfirmDialogFragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cosmic.gadsleaderboard.R
import com.cosmic.gadsleaderboard.apiservices.ApiClient
import com.cosmic.gadsleaderboard.apiservices.ApiHelper
import com.cosmic.gadsleaderboard.apiservices.submissionHelper
import com.cosmic.gadsleaderboard.ui.viewModel.MainViewModel
import com.cosmic.gadsleaderboard.ui.viewModel.SubmissionViewModel
import com.cosmic.gadsleaderboard.utils.Status
import com.cosmic.gadsleaderboard.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_submit.*
import kotlinx.android.synthetic.main.fragment_skills_leaderboard.*

class SubmissionActivity : AppCompatActivity(), ConfirmDialogFragment.OnSubmitButtonClicked {

    internal var handler = Handler()
    private var context: Context? = null

    val dialog = ConfirmDialogFragment()

    private lateinit var viewModel: SubmissionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        this.context = this@SubmissionActivity


        confirm_submission_button.setOnClickListener {

            if(firstNameEditText.text.isNotEmpty() &&
                lastNameEditText.text.isNotEmpty() &&
                emailEditText.text.isNotEmpty() &&
                    githubLinkEditText.text.isNotEmpty()) {

                dialog.setSubmitButtonClickListener(this)
                dialog.show(supportFragmentManager, dialog.tag)


            } else {
                Toast.makeText(this, "Input all fields", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onSubmitButtonClicked(button: View) {

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(null,
                submissionHelper(ApiClient.apiServiceInterface,
                    firstName = firstNameEditText.text.toString(),
                    lastName = lastNameEditText.text.toString(),
                    emailAddress = emailEditText.text.toString(),
                    projectLink = githubLinkEditText.text.toString()), "submission")

        ).get(SubmissionViewModel::class.java)


        viewModel.makeSubmission(
        ).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        skilliq_leaderboard_rv.visibility = View.VISIBLE
                        progress_bar_skills_board.visibility = View.GONE
                        resource.data?.let {
                            Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show()
                            dialog.dismiss()

                        }
                    }
                    Status.ERROR -> {
                        dialog.dismiss()

                    }
                    Status.LOADING -> {

                    }
                }
            }
        })

    }

}