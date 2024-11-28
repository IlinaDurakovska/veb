package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping
    public String getArtistPage(@RequestParam(required = false) String selectedTrackId, @RequestParam(required = false) String error, Model model){
        model.addAttribute("trackId", selectedTrackId);
        model.addAttribute("artists", artistService.listArtists());
        return "artistsList";
    }

    @PostMapping
    public String getSongDetails(@RequestParam(required = false) Long chosenArtist, @RequestParam(required = false) String trackId, @RequestParam(required = false) String error, Model model){
        Artist artist = artistService.findById(chosenArtist);
        Song song = songService.findByTrackId(trackId);
        song.addArtist(artist);

        model.addAttribute("songDet", song);
        return "songDetails";
    }

}
