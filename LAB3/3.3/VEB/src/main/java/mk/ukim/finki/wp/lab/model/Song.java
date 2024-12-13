package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
//@AllArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private static Long counter = (long) 0;

    private String trackId;
    private String title;
    private String genre;

    private int releaseYear;

    @Transient
    private List<Artist> performers;

    @ManyToOne
    private Album album;

    public Song() {
        trackId = "";
        title = "";
        genre = "";
        releaseYear = 0;
        performers = new ArrayList<>();
        album = null;
    }

    public Artist addArtist(Artist a) {
        for (Artist artist : performers) {
            if (artist.getId().equals(a.getId())) {
                performers.remove(artist);
                break;
            }
        }
        performers.add(a);
        return a;
    }

    public Song(String trackId, String title, String genre, int releaseYear, List<Artist> performers, Album album) {
        this.trackId = trackId;
        this.title = title;
        this.genre = "";
        this.releaseYear = releaseYear;
        this.performers = performers;

        //this.id = counter++;

        this.album = album;
    }
}
