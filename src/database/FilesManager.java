package database;

import music.Song;

/**
 * A class that help connect the database to the folders holding song/image files. To be used files in the same
 * package.
 */
class FilesManager {
    /**
     * Adds a new song to the Songs folder
     *
     * @param path the path to the song file
     * @return the new path to the song or null if exception caught
     */
    public static String addSong(String path){
        return null;
    }

    /**
     * Adds a new image to the Images folder
     *
     * @param path the path to the image file
     * @return the new path to the image or null if exception caught
     */
    public static String addImg(String path){
        return null;
    }

    /**
     * Deletes song from Songs folder
     * @param song the object wrapping the song file
     */
    public static void removeSong(Song song){

    }

    /**
     * Deletes image from Songs folder
     * @param path the path to the image bring deleted
     */
    public static void removeImg(String path){
        
    }
}
