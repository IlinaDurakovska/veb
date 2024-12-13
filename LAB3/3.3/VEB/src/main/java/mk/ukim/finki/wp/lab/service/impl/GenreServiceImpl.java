package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Genre;
import mk.ukim.finki.wp.lab.repository.jpa.GenreRepository;
import mk.ukim.finki.wp.lab.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepo;

    public GenreServiceImpl(GenreRepository genreRepo) {
        this.genreRepo = genreRepo;
    }

    @Override
    public List<Genre> listAll() {
        return genreRepo.findAll();
    }
}
