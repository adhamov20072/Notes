package com.alimardon.notes.Activity

import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alimardon.notes.Activity.room.Note
import com.alimardon.notes.databinding.RecyclerviewLayoutBinding

class recyclerAdapter : ListAdapter<Note, recyclerAdapter.MyViewHolder>(diffUtil) {
    class MyViewHolder(val binding: RecyclerviewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}