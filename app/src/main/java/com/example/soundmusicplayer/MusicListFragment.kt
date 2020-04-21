package com.example.soundmusicplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.soundmusicplayer.databinding.FragmentMusicListBinding

/**
 * A simple [Fragment] subclass.
 */
class MusicListFragment : Fragment() {
    private lateinit var binding: FragmentMusicListBinding
    private val adapter = MusicAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_music_list, container, false)

        val list:ArrayList<Music> = ArrayList()
        for(i in 1..20){
            list.add(Music(id = i.toString(), title = "title $i", singer = "Singer $i"))
        }
        adapter.submitList(list)
        binding.musicRecycler.layoutManager = LinearLayoutManager(activity,VERTICAL, false)
        binding.musicRecycler.adapter = adapter

        return binding.root
    }

}
