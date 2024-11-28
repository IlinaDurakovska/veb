package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class Album {
    private Long id;
    private static Long counter = (long) 0;

    private String name;
    private String genre;
    private String releaseYear;

    public Album(String name, String genre, String releaseYear) {
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.id = counter++;
    }
}
