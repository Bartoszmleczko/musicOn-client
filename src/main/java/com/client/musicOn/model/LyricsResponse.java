package com.client.musicOn.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LyricsResponse {
    @SerializedName("lyrics")
    @Expose
    private String lyrics;

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
