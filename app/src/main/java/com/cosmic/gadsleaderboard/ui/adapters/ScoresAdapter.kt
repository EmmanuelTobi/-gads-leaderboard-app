package com.cosmic.gadsleaderboard.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cosmic.gadsleaderboard.R
import com.cosmic.gadsleaderboard.models.LeaderboardScoresModelItem
import kotlinx.android.synthetic.main.leaderboard_item.view.*

class ScoresAdapter(private val leaderboardscoresitem: ArrayList<LeaderboardScoresModelItem>) : RecyclerView.Adapter<ScoresAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(leaderboardHoursItem: LeaderboardScoresModelItem) {
            itemView.apply {
                name.text = leaderboardHoursItem.name
                content.text = leaderboardHoursItem.scores.toString() + " skill IQ score, " + leaderboardHoursItem.country
                Glide.with(imageView.context)
                    .load(leaderboardHoursItem.badgeUrl)
                    .into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.leaderboard_item, parent, false)
        )

    override fun getItemCount(): Int = leaderboardscoresitem.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(leaderboardscoresitem[position])
    }

    fun addSkillsScores(leaderboarditem: List<LeaderboardScoresModelItem>) {
        this.leaderboardscoresitem.apply {
            clear()
            addAll(leaderboarditem)
        }

    }

}