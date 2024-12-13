package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;

import java.util.List;

public interface AlbumService {
    public List<Album> findAll();
    public Album findById(Long id);
    List<Album> findByGenreId(Long id);
}
