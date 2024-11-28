package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // getters and setters
//@AllArgsConstructor
public class Artist {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
    private Long id;
    private static Long counter = (long) 0;

    private String firstName;
    private String lastName;
    private String bio;

    public Artist(String firstName, String lastName, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.id = counter++;
    }
}
