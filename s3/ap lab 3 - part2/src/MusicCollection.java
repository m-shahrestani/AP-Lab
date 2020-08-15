import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class to hold details of audio files.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MusicCollection
{
    // An ArrayList for storing the  music information of music files.
    private ArrayList<Music> files;
    // A player for the music files.
    private MusicPlayer player;

    /**
     * Create a MusicCollection
     */
    public MusicCollection()
    {
        files = new ArrayList<>();
        player = new MusicPlayer();
    }
    
    /**
     * Add a file to the collection.
     * @param file The file to be added.
     */
    public void addFile(Music file)
    {
        files.add(file);
    }

    /**
     * Return the number of files in the collection.
     * @return The number of files in the collection.
     */
    public int getNumberOfFiles()
    {
        return files.size();
    }
    
    /**
     * List a file from the collection.
     * @param index The index of the file to be listed.
     */
    public void listFile(int index)
    {
        if(validIndex(index)) {
            Music temp = files.get(index-1);
            System.out.println(temp.getAddress() + "-" + temp.getArtist() + "-" + temp.getYear());
        }
    }

    /**
     * Show a list of all the files in the collection.
     */
    public void listAllFiles()
    {
        for(Music temp : files)
        {
            System.out.println(temp.getAddress() + "-" + temp.getArtist() + "-" + temp.getYear());
        }
    }
    
    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    public void removeFile(int index)
    {
        if(validIndex(index)) {
            Music temp = files.get(index - 1);
            Iterator<Music> it = files.iterator();
            while (it.hasNext()) {
                if (it.next().equals(temp)) {
                    it.remove();
                    break;
                }
            }
        }
    }

    /**
     * Start playing a file in the collection.
     * Use stopPlaying() to stop it playing.
     * @param index The index of the file to be played.
     */
    public void startPlaying(int index)
    {
        if(validIndex(index)) {
            player.startPlaying(files.get(index - 1));
        }
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
    }


    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
   private boolean validIndex(int index)
    {
        if(index > 0 && index <= files.size()) {
            return true;
        }
        else {
                System.out.println("index isn't valid.");
                return false;
        }
        // The return value.
        // Set according to whether the index is valid or not.
     }

    /**
     * Show a list of all the favorite files in the collection.
     */
    public void listAllFilesFavorite()
    {
        int i=0;
        System.out.println("Favorite list:");
        for(Music temp : files)
        {
            if(temp.getIsFavorite()) {
                System.out.println(temp.getAddress() + "-" + temp.getArtist() + "-" + temp.getYear());
                i++;
            }
        }
        if(i==0)
        {
            System.out.println("is empty.");
        }
    }

    /**
     * Add a file to the favorite list.
     * @param index The index of the file to be added to favorite list.
     */
    public void addFavorite(int index)
    {
        if(validIndex(index))
        {
            if(!files.get(index - 1).getIsFavorite()) {
                files.get(index - 1).setIsFavorite(true);
                System.out.println(files.get(index - 1).getAddress() + "-" + files.get(index - 1).getArtist() + "-" + files.get(index - 1).getYear() + " added to favorite list.");
            }
            else
            {
                System.out.println("It has been in favorite list.");
            }
        }
    }

    /**
     * Remove a file from the favorite list.
     * @param index The index of the file to be removed from favorite list.
     */
    public void removeFavorite(int index)
    {
        if(validIndex(index))
        {
            if(files.get(index - 1).getIsFavorite()) {
                files.get(index - 1).setIsFavorite(false);
                System.out.println(files.get(index - 1).getAddress() + "-" + files.get(index - 1).getArtist() + "-" + files.get(index - 1).getYear() + " removed from favorite list.");
            }
            else
            {
                System.out.println("It hasn't been in favorite list.");
            }
        }
    }

    /**
     * Search Print the found list.
     * @param string The string of search
     */
    public void search(String string)
    {
        int i = 0;
        System.out.println("found list:");
        for(Music temp : files)
        {
            if(temp.getAddress().contains(string) || temp.getArtist().contains(string)) {
                System.out.println(temp.getAddress() + "-" + temp.getArtist() + "-" + temp.getYear());
                i++;
            }
        }
        if(i == 0) {
            System.out.println("not found.");
        }
    }
}