package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryArtistRepository;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final InMemoryArtistRepository artistRepo;

    public ArtistServiceImpl(InMemoryArtistRepository artistRepo) {
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
