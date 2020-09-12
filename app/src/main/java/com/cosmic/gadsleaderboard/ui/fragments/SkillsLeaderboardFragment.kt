package com.cosmic.gadsleaderboard.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmic.gadsleaderboard.R
import com.cosmic.gadsleaderboard.apiservices.ApiClient
import com.cosmic.gadsleaderboard.apiservices.ApiHelper
import com.cosmic.gadsleaderboard.apiservices.submissionHelper
import com.cosmic.gadsleaderboard.models.LeaderboardScoresModelItem
import com.cosmic.gadsleaderboard.ui.adapters.ScoresAdapter
import com.cosmic.gadsleaderboard.ui.viewModel.MainViewModel
import com.cosmic.gadsleaderboard.utils.Status
import com.cosmic.gadsleaderboard.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_skills_leaderboard.*

class SkillsLeaderboardFragment : Fragment() {

    companion object {
        fun newInstance() = SkillsLeaderboardFragment()
        private lateinit var adapter: ScoresAdapter
    }

    private lateinit var viewModel: MainViewModel

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiClient.apiServiceInterface), submissionHelper = null, "")
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        skilliq_leaderboard_rv.layoutManager = LinearLayoutManager(requireContext())
        adapter =
            ScoresAdapter(arrayListOf())
        skilliq_leaderboard_rv.addItemDecoration(
            DividerItemDecoration(
                skilliq_leaderboard_rv.context,
                (skilliq_leaderboard_rv.layoutManager as LinearLayoutManager).orientation
            )
        )
        skilliq_leaderboard_rv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getLeaderBoardBySkillIQ().observe(requireActivity(), Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        skilliq_leaderboard_rv.visibility = View.VISIBLE
                        progress_bar_skills_board.visibility = View.GONE
                        resource.data?.let {
                                users -> retrieveList(users)
                            Toast.makeText(requireContext(), users.size.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                    Status.ERROR -> {
                        skilliq_leaderboard_rv.visibility = View.VISIBLE
                        progress_bar_skills_board.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progress_bar_skills_board.visibility = View.VISIBLE
                        skilliq_leaderboard_rv.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<LeaderboardScoresModelItem>) {
        adapter.apply {
            addSkillsScores(users)
            notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_skills_leaderboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewModel()
        setupUI()
        setupObservers()
    }

}