package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryArtistRepository {

    public List<Artist> findAll(){
        return DataHolder.artists;
    }

    //public Artist save(Artist a){}
    //public void delete(Long id){}
    //public List<Artist> search(String keyword){}

    public Optional<Artist> findById(Long id){
        return DataHolder.artists.stream().filter(artist -> artist.getId().equals(id)).findFirst();
    }
}