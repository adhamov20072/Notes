package com.alimardon.notes.Activity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alimardon.notes.Activity.room.Note
import com.alimardon.notes.databinding.RecyclerviewLayoutBinding

class recyclerAdapter :
    androidx.recyclerview.widget.ListAdapter<Note, recyclerAdapter.MyViewHolder>(diffUtil) {
    class MyViewHolder(val binding: RecyclerviewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var listner: OnItemClickListener? = null

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RecyclerviewLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.title.text=item.title
        holder.binding.description.text=item.description
    }

    interface OnItemClickListener {
        fun delete(note: Note)
        fun update(note: Note)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        listner = onItemClickListener
    }
}