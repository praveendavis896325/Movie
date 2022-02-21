package com.praveen.omdb.detail.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.praveen.omdb.R
import com.praveen.omdb.detail.data.DetailRating

class RatingAdapter(var mdetailsRemoteList: MutableList<DetailRating>) : RecyclerView.Adapter<RatingAdapter.DetailsViewHolder>() {


    override fun getItemCount(): Int {
        return if (mdetailsRemoteList.size > 0) mdetailsRemoteList.size else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ratings_view, parent, false)
        return DetailsViewHolder(view)
    }

    fun addItems(mList: List<DetailRating>) {
        mdetailsRemoteList.addAll(mList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mdetailsRemoteList.clear()
    }


    inner class DetailsViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDetailName: TextView = itemView.findViewById(R.id.detail_tv) as TextView
        var playBtn: TextView = itemView.findViewById(R.id.rating) as TextView

    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val details = mdetailsRemoteList[position]
        holder.tvDetailName.text = details.Source
        holder.playBtn.text = details.Value
    }



}
