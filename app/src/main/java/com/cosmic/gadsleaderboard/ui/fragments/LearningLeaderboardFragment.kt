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
import com.cosmic.gadsleaderboard.models.LeaderboardHoursModelItem
import com.cosmic.gadsleaderboard.ui.adapters.LearningAdapter
import com.cosmic.gadsleaderboard.ui.viewModel.MainViewModel
import com.cosmic.gadsleaderboard.utils.Status
import com.cosmic.gadsleaderboard.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_learning_leaderboard.*

class LearningLeaderboardFragment : Fragment() {

    companion object {
        fun newInstance() = LearningLeaderboardFragment()
        private lateinit var adapter: LearningAdapter
    }

    private lateinit var viewModel: MainViewModel

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiClient.apiServiceInterface))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        learning_leaderboard_rv.layoutManager = LinearLayoutManager(requireContext())
        adapter =
            LearningAdapter(arrayListOf())
        learning_leaderboard_rv.addItemDecoration(
            DividerItemDecoration(
                learning_leaderboard_rv.context,
                (learning_leaderboard_rv.layoutManager as LinearLayoutManager).orientation
            )
        )
        learning_leaderboard_rv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getLeaderBoardByHours().observe(requireActivity(), Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        learning_leaderboard_rv.visibility = View.VISIBLE
                        progress_bar_learning_board.visibility = View.GONE
                        resource.data?.let {
                                users -> retrieveList(users)
                            Toast.makeText(requireContext(), users.size.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                    Status.ERROR -> {
                        learning_leaderboard_rv.visibility = View.VISIBLE
                        progress_bar_learning_board.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progress_bar_learning_board.visibility = View.VISIBLE
                        learning_leaderboard_rv.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<LeaderboardHoursModelItem>) {
        adapter.apply {
            addLearningHours(users)
            notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_learning_leaderboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewModel()
        setupUI()
        setupObservers()

    }

}