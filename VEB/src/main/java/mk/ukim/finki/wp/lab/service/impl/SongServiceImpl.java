package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final ArtistRepository artistRepo;
    private final SongRepository songRepo;
    private final AlbumRepository albumRepo;

    public SongServiceImpl(ArtistRepository artistRepo, SongRepository songRepo, AlbumRepository albumRepo) {
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
        return songRepo.addArtistToSong(artist, song);
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
        return songRepo.findById(id);
    }

    @Override
    public void addSong(String name, String trackId, String genre, int year, Album album) {
        songRepo.addSong(name, trackId, genre, year, album);
    }

    @Override
    public Song editSong(Long songId, String title, String genre, int releaseYear, String trackId, Long albumId) {
        return songRepo.editSong(songId, title, genre, releaseYear, trackId, albumRepo.findById(albumId));
    }

}
