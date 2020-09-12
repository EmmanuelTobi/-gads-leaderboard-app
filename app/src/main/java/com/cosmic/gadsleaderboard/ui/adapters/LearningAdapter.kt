package com.cosmic.gadsleaderboard.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cosmic.gadsleaderboard.R
import com.cosmic.gadsleaderboard.models.LeaderboardHoursModelItem
import kotlinx.android.synthetic.main.leaderboard_item.view.*

class LearningAdapter(private val leaderboardhoursitem: ArrayList<LeaderboardHoursModelItem>) : RecyclerView.Adapter<LearningAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(leaderboardHoursItem: LeaderboardHoursModelItem) {
            itemView.apply {
                name.text = leaderboardHoursItem.name
                content.text = leaderboardHoursItem.hours.toString() + " learning hours, " + leaderboardHoursItem.country
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

    override fun getItemCount(): Int = leaderboardhoursitem.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(leaderboardhoursitem[position])
    }

    fun addLearningHours(leaderboarditem: List<LeaderboardHoursModelItem>) {
        this.leaderboardhoursitem.apply {
            clear()
            addAll(leaderboarditem)
        }

    }

}