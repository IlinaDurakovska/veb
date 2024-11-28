package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepo;

    public ArtistServiceImpl(ArtistRepository artistRepo) {
        this.artistRepo = artistRepo;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepo.findAll();
    }

    @Override
    public Artist findById(Long id) {
        return artistRepo.findById(id).orElse(null);
    }
}
