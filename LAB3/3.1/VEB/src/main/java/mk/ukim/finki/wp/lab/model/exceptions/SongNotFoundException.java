package mk.ukim.finki.wp.lab.model.exceptions;

public class SongNotFoundException extends Exception {

    public SongNotFoundException() {
        super("Song not found!");
    }

}
