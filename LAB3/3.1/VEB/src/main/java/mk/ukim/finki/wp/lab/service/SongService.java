package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.SongNotFoundException;

import java.util.List;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);
    void deleteById(Long id);
    Song findById(Long id);
    void addSong(String name, String trackId, String genre, int year, Album album);
    Song editSong(Long songId, String title, String genre, int releaseYear, String trackId, Long albumId) throws SongNotFoundException;
}
