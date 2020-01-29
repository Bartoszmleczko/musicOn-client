package com.client.musicOn.controller;

import com.client.musicOn.dao.RoleRepository;
import com.client.musicOn.dao.UserRepository;
import com.client.musicOn.entity.Roles;
import com.client.musicOn.entity.Users;
import com.client.musicOn.model.ITunesResponse;
import com.client.musicOn.model.LyricsResponse;
import com.client.musicOn.model.Result;
import com.client.musicOn.model.UnregisteredUser;
import com.client.musicOn.service.ItunesSerivce;
import com.client.musicOn.service.LyricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ItunesSerivce iTunesService;
    @Autowired
    private LyricsService lyricsService;


    @GetMapping
    public String showHome(Model theModel){

        ITunesResponse response = iTunesService.getAristByName("Gojira");
        List<Result> tracks = response.getResults();
        theModel.addAttribute("tracks" , tracks);
        return "home";

    }

    @GetMapping("/findSong/{trackId}")
    public String getTrackSubpage(@PathVariable("trackId") Integer trackId,
                                  Model theModel){

        Result result = iTunesService.getArtistById(trackId);
        LyricsResponse lyrics = lyricsService.getLyrics(result.getArtistName(),result.getTrackName());
        theModel.addAttribute("lyrics",lyrics);
        theModel.addAttribute("track",result);

        return "track-page";
    }



}
