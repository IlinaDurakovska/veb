package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.SongNotFoundException;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryAlbumRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryArtistRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemorySongRepository;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final InMemoryArtistRepository artistRepo;

    //private final InMemorySongRepository songRepo;
    //private final InMemoryAlbumRepository albumRepo;

    private final SongRepository songRepo;
    private final AlbumRepository albumRepo;

    public SongServiceImpl(InMemoryArtistRepository artistRepo, SongRepository songRepo, AlbumRepository albumRepo) {
        this.artistRepo = artistRepo;
        this.songRepo = songRepo;
        this.albumRepo = albumRepo;
    }

    @Override
    public List<Song> listSongs() {
        return songRepo.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        //return songRepo.addArtistToSong(artist, song);
        songRepo.findById(song.getId()).ifPresent(s -> s.addArtist(artist));
        return artist;
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepo.findByTrackId(trackId);
    }

    @Override
    public void deleteById(Long id) {
        songRepo.deleteById(id);
    }

    @Override
    public Song findById(Long id) {
        //return songRepo.findById(id);
        return songRepo.findById(id).orElse(null);
    }

    @Override
    public void addSong(String title, String trackId, String genre, int year, Album album) {
        //songRepo.addSong(name, trackId, genre, year, album);
        songRepo.save(new Song(trackId, title, genre, year, new ArrayList<>(), album));
    }

    @Override
    public Song editSong(Long songId, String title, String genre, int releaseYear, String trackId, Long albumId) throws SongNotFoundException {
        //return songRepo.editSong(songId, title, genre, releaseYear, trackId, albumRepo.findById(albumId));
        Album a = albumRepo.findById(albumId).orElse(null);
        Song s = songRepo.findById(songId).orElse(null);
        if(s == null){
            throw new SongNotFoundException();
        }
        s.setTitle(title);
        s.setGenre(genre);
        s.setReleaseYear(releaseYear);
        s.setTrackId(trackId);
        s.setAlbum(a);
        return songRepo.save(s);
    }

    @Override
    public List<Song> findByAlbum(Long albumId) {
        return songRepo.findAllByAlbumId(albumId);
    }

}
