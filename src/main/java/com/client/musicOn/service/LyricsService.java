package com.client.musicOn.service;

import com.client.musicOn.model.LyricsResponse;

public interface LyricsService {

    public LyricsResponse getLyrics(String artist, String song);

}
