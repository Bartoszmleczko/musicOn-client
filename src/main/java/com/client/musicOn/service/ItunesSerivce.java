package com.client.musicOn.service;

import com.client.musicOn.model.ITunesResponse;
import com.client.musicOn.model.Result;

public interface ItunesSerivce {
    public ITunesResponse getAristByName(String artistName);
    public Result getArtistById(Integer artistId);
}
