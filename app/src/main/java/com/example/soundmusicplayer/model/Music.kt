package com.example.soundmusicplayer.model

data class Music @JvmOverloads constructor (
        val id: Long,
        val title: String,
        val singer: String,
        @JvmField val album: String = "",
        @JvmField val picPath: String = ""
)