package com.example.soundmusicplayer

data class Music (
        val id: String,
        val title: String,
        val album: String = "",
        val singer: String,
        val picPath: String = ""
)