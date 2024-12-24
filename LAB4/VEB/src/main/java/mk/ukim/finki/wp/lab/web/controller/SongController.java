package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.SongNotFoundException;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller //vrakja view kon korisnikot
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("songs", songService.listSongs());
        return "listSongs";
    }

    @PostMapping
    public String getArtists(@RequestParam(name = "chosenTrack", required = false) String selectedTrackId, Model model){
        model.addAttribute("selectedTrackId", selectedTrackId);
        return "redirect:/artist?selectedTrackId=" + selectedTrackId;
    }


    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSong(@PathVariable Long id){
        this.songService.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{songId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editSongForm(@PathVariable Long songId, Model model){
        Song song = songService.findById(songId);
        if (song != null){
            model.addAttribute("song", song);
            model.addAttribute("albums", albumService.findAll());
            model.addAttribute("songId", songId);
            model.addAttribute("editSong", true);
            return "add-song";
        }
        //model.addAttribute("error", new SongNotFoundException());
        return "redirect:/songs?error=SongNotFound";
    }

    @PostMapping("/edit/{songId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editSong(@PathVariable Long songId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam int year,
                           @RequestParam String trackId,
                           @RequestParam Long albumId) {
        try {
            songService.editSong(songId, title, genre, year, trackId, albumId);
        } catch (SongNotFoundException e) {
            return "redirect:/songs?error=SongNotFound";
        }
        return "redirect:/songs";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAddSongPage(Model model){
        model.addAttribute("editSong", false);
        model.addAttribute("albums", albumService.findAll());
        //model.addAttribute("song", null);
        return "add-song";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveSong(@RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam int year,
                           @RequestParam Long albumId){
        Album album = albumService.findById(albumId);
        songService.addSong(title,trackId,genre,year,album);
        return "redirect:/songs";
    }
}
