package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
//@AllArgsConstructor
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private static Long counter = (long) 0;

    private String name;
    //private String genre;

    @ManyToOne
    private Genre genre;


    private String releaseYear;

    @OneToMany(mappedBy = "album")
    private List<Song> songs;


    public Album(String name, String genre, String releaseYear) {
        this.name = name;
        this.genre = null;
        this.releaseYear = releaseYear;
        //this.id = counter++;
    }

    public Album() {
        this.name = "";
        this.genre = null;
        this.releaseYear = "";
    }
}

//INSERT INTO ALBUM VALUES(1, 'G', 'n', 'ry');
//INSERT INTO ALBUM VALUES(2, 'Gg', 'gn', 'gry');