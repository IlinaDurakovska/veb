package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemorySongRepository {

    public List<Song> findAll(){
        return DataHolder.songs;
    }

    public Song findByTrackId(String trackId){
        return DataHolder.songs.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst().orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song){
        if (findByTrackId(song.getTrackId()) == null)
            return null;
        return findByTrackId(song.getTrackId()).addArtist(artist);
    }

    public void deleteById(Long Id){
        DataHolder.songs.removeIf(song -> song.getId().equals(Id));
    }

    public Song findById(Long id){
        return DataHolder.songs.stream().filter(song -> song.getId().equals(id)).findFirst().orElse(null);
    }

    public void addSong(String title, String trackId, String genre, int year, Album album){
        List<Artist> artists = new ArrayList<>();
        DataHolder.songs.add(new Song(trackId, title, genre, year, artists, album));
    }

    public Song editSong(Long songId, String title, String  genre, int year, String trackId, Album album){
        Song s =  DataHolder.songs.stream().filter(song -> song.getId().equals(songId)).findFirst().orElse(null);
        s.setTitle(title);
        s.setGenre(genre);
        s.setReleaseYear(year);
        s.setTrackId(trackId);
        s.setAlbum(album);

        return s;
    }
}
