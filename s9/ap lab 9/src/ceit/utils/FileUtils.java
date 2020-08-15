package ceit.utils;

import ceit.model.Note;
import java.io.*;
import java.util.Date;

/**
 * A class with static methods to hold file utils.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class FileUtils {

    //a String for file address
    private static final String NOTES_PATH = "./notes/";

    //It's a static initializer. It's executed when the class is loaded.
    //It's similar to constructor
    static {
        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
    }

    /**
     * get The Files In Directory.
     *
     * @return  file collection.
     */
    public static File[] getFilesInDirectory() {
        return new File(NOTES_PATH).listFiles();
    }

    /**
     * a method for file reading.
     *
     * @param file file.
     * @return a string of content.
     */
    public static String fileReader(File file) {
        //TODO: Phase1: read content from file
        StringBuilder content = new StringBuilder();
        BufferedReader objReader = null;
        try {
            String strCurrentLine;

            objReader = new BufferedReader(new FileReader(file));

            while ((strCurrentLine = objReader.readLine()) != null) {

                content.append(strCurrentLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return content.toString();
    }

    /**
     * a method for file writing.
     *
     * @param content a String.
     */
    public static void fileWriter(String content) {
        //TODO: write content on file
        String fileName = getProperFileName(content);
        BufferedWriter bw = null;
        try {
            File file = new File(NOTES_PATH + fileName + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(content);
            System.out.println("File written Successfully");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
    }


    /**
     * a method for file reading with InputStream.
     *
     * @param file file.
     * @return a string of content.
     */
    //TODO: Phase1: define method here for reading file with InputStream
    public static String readFileStream(File file)
    {
        String result = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int i = 0;
            while ((i=fileInputStream.read())!=-1)
            {
                char in = (char)i;
                result = result + Character.toString(in);
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * a method for file writing with OutputStream.
     *
     * @param content a String.
     */
    //TODO: Phase1: define method here for writing file with OutputStream
    public static void writeFileStream(String content) {
        String fileName = getProperFileName(content);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(NOTES_PATH + fileName);
            byte[] strToByteArray = content.getBytes();
            fileOutputStream.write(strToByteArray);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * a method for file reading with object serialization.
     *
     * @param file file.
     * @return a string of content.
     */
    //TODO: Phase2: proper methods for handling serialization
    public static String readObject(File file) {
        try (FileInputStream fs = new FileInputStream(file)){
            ObjectInputStream os = new ObjectInputStream(fs);

            return os.readObject().toString();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * a method for file writing with object serialization.
     *
     * @param content a String.
     */
    public static void writeObject(String content) {
        String fileName = getProperFileName(content);
        try (FileOutputStream fs = new FileOutputStream(NOTES_PATH + fileName + ".note")){
            ObjectOutputStream  os = new ObjectOutputStream(fs);
            os.writeObject(new Note(fileName, content, new Date().toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a method for initializing name address.
     *
     * @param content a string of content.
     * @return a string of name address.
     */
    private static String getProperFileName(String content) {
        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc);
        }
        if (!content.isEmpty()) {
            return content;
        }
        return System.currentTimeMillis() + "_new file.txt";
    }
}
