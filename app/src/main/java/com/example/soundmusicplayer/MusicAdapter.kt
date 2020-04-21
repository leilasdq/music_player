package com.example.soundmusicplayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soundmusicplayer.databinding.ListItemsBinding

class MusicAdapter: ListAdapter<Music, MusicAdapter.MusicViewHolder>(MusicDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MusicViewHolder private constructor(private val binding: ListItemsBinding): RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun createViewHolder(parent: ViewGroup): MusicViewHolder {
                val binding = ListItemsBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                return MusicViewHolder(binding)
            }
        }

        fun bind(music: Music){
            binding.musicName.text = music.title
            binding.musicSinger.text = music.singer
        }
    }
}

class MusicDiffUtils: DiffUtil.ItemCallback<Music>() {
    override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem == newItem
    }

}