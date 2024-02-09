package com.example.a20231111hwmaterialdesign;

import com.orm.SugarRecord;
import androidx.annotation.NonNull;
import com.orm.util.SugarCursorFactory;


import java.sql.Blob;
import java.sql.SQLException;

public class Album extends SugarRecord {
    private String title;
    private String artist;
    private byte[] image;
    private int year;

    public Album() {
    }

    public Album(String title, String artist, byte[] image, int year) {
        this.title = title;
        this.artist = artist;
        this.image = image;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public byte[] getImage() {
        return image;
    }

    public int getYear() {
        return year;
    }
    @NonNull
    @Override
    public String toString() {
        return title;
    }

    @Override
    public long save() {
        if(image != null) {
            SugarCursorFactory.adjustWindowSize(image.length);
        }
        return super.save();
    }
}
