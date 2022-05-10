package com.s4days.feature_launches.data.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.s4days.core_network.data.model.SpaceXLaunch
import com.s4days.feature_launches.R
import com.s4days.feature_launches.databinding.LaunchListItemBinding
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

internal typealias LaunchInfo = (String) -> Unit

internal class LaunchAdapter(
    private val openLaunchInfo: LaunchInfo
) : RecyclerView.Adapter<LaunchAdapter.LaunchViewHolder>() {

    private var items = listOf<SpaceXLaunch>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(launches: List<SpaceXLaunch>){
        items = launches
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder =
        LaunchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.launch_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.count()

    inner class LaunchViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = LaunchListItemBinding.bind(itemView)
        fun bind(item: SpaceXLaunch){
            val df = DateFormat.getDateTimeInstance()
            binding.name.text = item.name
            binding.date.text = item.dateUnix?.let { df.format(Date(it*1000)) }
            item.links?.patch?.small?.let {
                Picasso.get().load(it).into(binding.image)
            }

            itemView.setOnClickListener {
                item.id?.let { id -> openLaunchInfo(id) }
            }
        }
    }

}
