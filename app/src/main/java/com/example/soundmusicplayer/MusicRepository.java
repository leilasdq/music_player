package com.example.soundmusicplayer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.soundmusicplayer.model.Music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MusicRepository {
//    private static Context mContext;
    private static ArrayList<Music> mMusicLists = new ArrayList<>();

//    public MusicRepository(Context context) {
//        mContext = context.getApplicationContext();
//    }

    public static List<Music> getSongLists(Context context){
        Long id;
        String title, album, artist, albumId, picPath;

        ContentResolver musicResolver = context.getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor =  musicResolver.query(musicUri, null, null, null, null);

        if (musicCursor!=null){
            musicCursor.moveToFirst();
            while (!musicCursor.isAfterLast()){
                id = musicCursor.getLong(musicCursor.getColumnIndex(MediaStore.Audio.Media._ID));
                title = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                artist = musicCursor.getString((musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                mMusicLists.add(new Music(id, title, artist));

                musicCursor.moveToNext();
            }
            musicCursor.close();
        }
        return mMusicLists;
    }

    public static List<Music> getOrderedMusic(Context context){
        getSongLists(context);
        Collections.sort(mMusicLists, new Comparator<Music>() {
            @Override
            public int compare(Music o1, Music o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        return mMusicLists;
    }
}
