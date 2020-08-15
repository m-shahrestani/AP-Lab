package ceit.model;

import java.io.Serializable;

/**
 * A class to hold Note that implements Serializable.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Note implements Serializable {
    //note title
    private String title;
    //note content
    private String content;
    //note date
    private String date;

    /**
     * Create a new Note with given title and content and date.
     *
     * @param title title of Note.
     * @param content content  of Note.
     * @param date date of Note.
     */
    public Note(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    /**
     * an Override method
     * get a String for Note.
     * @return a String.
     */
    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}

