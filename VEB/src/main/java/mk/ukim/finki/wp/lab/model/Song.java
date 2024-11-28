package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
//@AllArgsConstructor
public class Song {
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private List<Artist> performers;

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private static Long counter = (long) 0;

    private Album album;

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
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = performers;

        this.id = counter++;

        this.album = album;
    }
}
