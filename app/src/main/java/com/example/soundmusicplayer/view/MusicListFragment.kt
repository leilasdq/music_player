package com.example.soundmusicplayer.view

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.soundmusicplayer.MusicRepository
import com.example.soundmusicplayer.adapter.MusicAdapter
import com.example.soundmusicplayer.R
import com.example.soundmusicplayer.databinding.FragmentMusicListBinding
import com.example.soundmusicplayer.model.Music

/**
 * A simple [Fragment] subclass.
 */
class MusicListFragment : Fragment() {
    private lateinit var binding: FragmentMusicListBinding
    private val adapter = MusicAdapter()
    companion object {
        private const val STORAGE_PERMISSION_CODE = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_music_list, container, false)

        if (ContextCompat.checkSelfPermission(requireActivity().applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED){
                setUpRecycle()
        } else {
            getPermission()
        }

        return binding.root
    }

    private fun setUpRecycle() {
        adapter.submitList(MusicRepository.getOrderedMusic(activity))
        binding.musicRecycler.layoutManager = LinearLayoutManager(activity, VERTICAL, false)
        binding.musicRecycler.adapter = adapter
    }

    private fun getPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)){
            AlertDialog.Builder(requireActivity())
                    .setTitle("Permission is need")
                    .setMessage("Reading storage permission is required for find your songs in your device.\nplease confirm permission.")
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                        ActivityCompat
                                .requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    })
                    .setCancelable(false).create().show()
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
        } else {
            ActivityCompat
                    .requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                setUpRecycle()
            } else {
                Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
