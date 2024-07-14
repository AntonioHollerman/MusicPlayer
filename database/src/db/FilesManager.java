package db;

import java.nio.file.Path;

/**
 * A class that help connect the database to the folders holding song/image files. To be used files in the same
 * package.
 */
class FilesManager {
    public static final Path SONGS_FOLDER_PATH = Path.of("SongsFolder\\");
    public static final Path IMAGES_FOLDER_PATH = Path.of("ImagesFolder\\");
    // TODO: Initialize folders and property files
    static {

    }
    /**
     * Adds a new song to the Songs folder
     *
     * @param path the path to the song file
     * @return the new path to the song or null if exception caught
     */
    public static Path addSong(Path path){
        return null;
    }

    /**
     * Adds a new image to the Images folder
     *
     * @param path the path to the image file
     * @return the new path to the image or null if exception caught
     */
    public static Path addImg(Path path){
        return null;
    }

    /**
     * Deletes song from Songs folder
     * @param songPath path to song file
     */
    public static void removeSong(Path songPath){

    }

    /**
     * Deletes image from Songs folder
     * @param path the path to the image bring deleted
     */
    public static void removeImg(Path path){
        
    }

    public static void main(String[] args) {
        System.out.println(SONGS_FOLDER_PATH.toAbsolutePath());
    }
}
