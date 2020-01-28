package com.client.musicOn.service;

import com.client.musicOn.model.LyricsResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LyricsClient implements LyricsService {

    private String restUrl;
    private RestTemplate restTemplate;

    @Autowired
    public LyricsClient(@Value("${lyrics.url}") String restUrl, RestTemplate restTemplate) {
        this.restUrl = restUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public LyricsResponse getLyrics(String artist, String song) {
        String s = restTemplate.getForObject(restUrl+"/" + artist + "/" + song,String.class);
        LyricsResponse response = new Gson().fromJson(s,LyricsResponse.class);
        return response;
    }
}
