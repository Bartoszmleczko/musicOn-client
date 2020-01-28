package com.client.musicOn.service;

import com.client.musicOn.model.ITunesResponse;
import com.client.musicOn.model.Result;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class ITunesClient implements ItunesSerivce {

    private String theUrl;
    private RestTemplate restTemplate;

    @Autowired
    public ITunesClient(@Value("${itunes.url}") String theUrl, RestTemplate restTemplate) {
        this.theUrl = theUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public ITunesResponse getAristByName(String artistName) {
        String s = restTemplate.getForObject(theUrl + "search?term=" + artistName,String.class);
        ITunesResponse response= new Gson().fromJson(s,ITunesResponse.class);
        List<Result> result = response.getResults();
//        for(Result r : result){
//            System.out.println(r.getArtistId() + " " + r.getArtistName() + r.getTrackName());
//        }

        return response;
    }

    @Override
    public Result getArtistById(Integer artistId) {
        String s = restTemplate.getForObject(theUrl + "lookup?id=" + artistId,String.class);
        ITunesResponse response= new Gson().fromJson(s,ITunesResponse.class);

        Result result = response.getResults().get(0);
        return result;
    }
}
