package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.SongNotFoundException;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.GenreService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller //vrakja view kon korisnikot
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;
    private final GenreService genreService;

    public SongController(SongService songService, AlbumService albumService, GenreService genreService) {
        this.songService = songService;
        this.albumService = albumService;
        this.genreService = genreService;
    }


    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("showAlbumsByGenre", false);

        //if(genreId != null && !genreId.isEmpty()){
        //    model.addAttribute("albumsGenre", albumService.findByGenreId(Long.valueOf(genreId)));
        //    model.addAttribute("showAlbumsByGenre", true);

        //    List<Album> albums = albumService.findByGenreId(Long.valueOf(genreId));
        //    ArrayList<Song> songsg = new ArrayList<>();
        //    for (Album album : albums){
        //        songsg.addAll(songService.findByAlbum(album.getId()));
        //    }

        //    model.addAttribute("songsGenre", songsg);
        //}

        model.addAttribute("songs", songService.listSongs());

        model.addAttribute("genres", genreService.listAll());

        return "listSongs";
    }

    @PostMapping
    public String getArtists(@RequestParam(name = "chosenTrack", required = false) String selectedTrackId, Model model){
        model.addAttribute("selectedTrackId", selectedTrackId);
        return "redirect:/artist?selectedTrackId=" + selectedTrackId;
    }


    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        this.songService.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{songId}")
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
    public String getAddSongPage(Model model){
        model.addAttribute("editSong", false);
        model.addAttribute("albums", albumService.findAll());
        //model.addAttribute("song", null);
        return "add-song";
    }

    @PostMapping("/add")
    public String saveSong(@RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam int year,
                           @RequestParam Long albumId){
        Album album = albumService.findById(albumId);
        songService.addSong(title,trackId,genre,year,album);
        return "redirect:/songs";
    }

    @PostMapping("/genres")
    public String showAlbumsByGenre(@RequestParam Long chosenGenre, Model model){

        model.addAttribute("songs", songService.listSongs());
        model.addAttribute("genres", genreService.listAll());
        model.addAttribute("albumsGenre", albumService.findByGenreId(chosenGenre));
        List<Album> albums = albumService.findByGenreId(chosenGenre);
        ArrayList<Song> songs = new ArrayList<>();
        for (Album album : albums){
            songs.addAll(songService.findByAlbum(album.getId()));
        }
        model.addAttribute("songsGenre", songs);
        model.addAttribute("showAlbumsByGenre", true);

        return "listSongs";
    }
}
