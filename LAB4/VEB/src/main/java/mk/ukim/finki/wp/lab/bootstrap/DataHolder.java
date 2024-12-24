package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Artist> artists;
    public static List<Song> songs;
    public static List<Album> albums;

    @PostConstruct
    public void init(){
        artists = new ArrayList<>();
        songs = new ArrayList<>();
        albums = new ArrayList<>();

        artists.add(new Artist("a", "A", "aaa"));
        artists.add(new Artist("b", "B", "bbb"));
        artists.add(new Artist("c", "C", "ccc"));
        artists.add(new Artist("d", "D", "ddd"));
        artists.add(new Artist("e", "E", "eee"));

        ArrayList<Artist> tmp = new ArrayList<>();
        tmp.add(artists.get(0));
        tmp.add(artists.get(1));

        albums.add(new Album("album1", "A", "2000"));
        albums.add(new Album("album2", "B", "2001"));
        albums.add(new Album( "album3", "C", "2005"));
        albums.add(new Album("album4", "D", "2007"));
        albums.add(new Album("album5", "E", "2010"));

        songs.add(new Song("0", "song1", "rock", 2000, tmp, albums.get(2)));
        songs.add(new Song("1", "song2", "rnb", 2005, tmp, albums.get(2)));
        songs.add(new Song("2", "song3", "blues", 1990, tmp, albums.get(2)));
        songs.add(new Song("3", "song4", "pop", 2020, tmp, albums.get(0)));
        songs.add(new Song("4", "song5", "rock", 2002, tmp, albums.get(0)));
    }
}
