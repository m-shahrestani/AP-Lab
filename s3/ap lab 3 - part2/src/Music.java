/**
 * A class to hold address and artist and year for music file
 *
 * @author Mohammadreza
 * @version 2020.03.12
 */

public class Music {
    // the Music’s address
    private String address;
    // the Music’s artist
    private String artist;
    // the Music’s year
    private int year;
    // Indicates the situation of favorite
    private boolean isFavorite;

    /**
     * Create a new Music with a given address and artist and year.
     *
     * @param address address of Music
     * @param artist artist of Music
     * @param year year of Music*
     */
    public Music(String address, String artist, int year) {
        this.address = address;
        this.artist = artist;
        this.year = year;
        isFavorite = false;
    }

    /**
     * get the address of a Music
     * @return name field
     */
    public String getAddress() {
        return address;
    }

    /**
     * get the artist of a Music
     * @return artist field
     */
    public String getArtist() {
        return artist;
    }

    /**
     * get the year of a Music
     * @return year field
     */
    public int getYear() {
        return year;
    }

    /**
     * get the situation of favorite
     * @return isFavorite field
     */
    public boolean getIsFavorite()
    {
        return isFavorite;
    }

    /**
     * @param isFavorite set situation of favorite
     */
    public void setIsFavorite(boolean isFavorite)
    {
        this.isFavorite = isFavorite;
    }

}