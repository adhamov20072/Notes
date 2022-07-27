package com.alimardon.app13

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alimardon.app13.databinding.ListItemBinding

class expAdapter : ListAdapter<inson, expAdapter.MyViewHolder>(diffcallback) {
    class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
    companion object {
        val diffcallback = object : DiffUtil.ItemCallback<inson>() {
            override fun areItemsTheSame(oldItem: inson, newItem: inson): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: inson, newItem: inson): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.listimagebutton.setOnClickListener {
            if (holder.binding.expid.visibility == View.GONE) {
                holder.binding.expid.visibility = View.VISIBLE
                holder.binding.listimagebutton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            } else {
                holder.binding.expid.visibility = View.GONE
                holder.binding.listimagebutton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
            }
        }
        holder.binding.title.text = item.name
        holder.binding.massage.text = item.job
    }
}