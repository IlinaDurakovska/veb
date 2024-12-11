package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final SongService songService;


    public AlbumController(final AlbumService albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    @GetMapping
    public String getAlbumsPage(Model model) {
        List<Album> albums = albumService.findAll();

        model.addAttribute("albs", albums);
        model.addAttribute("showSongs", false);

        return "albums";
    }

    @PostMapping
    public String showAlbumSongs(@RequestParam Long chosenAlbum, Model model) {
        List<Album> albums = albumService.findAll();
        model.addAttribute("albs", albums);

        List<Song> songs = songService.findByAlbum(chosenAlbum);

        model.addAttribute("showSongs", true);
        model.addAttribute("albumSongs", songs);

        return "albums";
    }

}
