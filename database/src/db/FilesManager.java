package db;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * A class that help connect the database to the folders holding song/image files. To be used files in the same
 * package.
 */
class FilesManager {
    public static final Path SONGS_FOLDER_PATH = Path.of(".SongsFolder\\");
    public static final Path IMAGES_FOLDER_PATH = Path.of(".ImagesFolder\\");
    public static final Path SONGS_INDEX = SONGS_FOLDER_PATH.resolve(Path.of("Index.ser"));
    public static final Path IMAGES_INDEX = IMAGES_FOLDER_PATH.resolve(Path.of("Index.ser"));

    public static final List<String> VALID_SONG_PATHS = List.of(".mp3", ".aac", ".wav", ".aiff");
    public static final List<String> VALID_IMAGE_PATHS = List.of(".jpeg", ".jpg", ".png", ".gif", ".bmp", ".wbmp");

    static {
        try{
            if (!Files.exists(SONGS_FOLDER_PATH)){
                Files.createDirectories(SONGS_FOLDER_PATH);
            }
        } catch (IOException e){
            e.fillInStackTrace();
        }
    }

    // Keep track oh what next increment to give the next file
    public static class FilesIndex implements Closeable, Serializable {
        private final transient Path path;
        private Integer index;

        private FilesIndex(Path path, Integer index){
            this.path = path;
            this.index = index;
        }

        public static FilesIndex of(Path path) throws IOException, ClassNotFoundException{
            if (!Files.exists(path)){
                return new FilesIndex(path, 0);
            }
            FilesIndex fileRead = (FilesIndex) new ObjectInputStream(
                    new FileInputStream(
                            path.toFile()
                    )
            ).readObject();
            return new FilesIndex(path, fileRead.index);
        }

        /**
         * Preform post increment on current next file index
         * @return the next index
         */
        public int getNextIndex(){
            return index++;
        }
        @Override
        public void close() throws IOException {
            Files.createDirectories(path.getParent());
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path.toFile()))){
                out.writeObject(this);
            }
        }
    }

    private static String spliceFileType(Path path){
        String fileName = path.getFileName().toString();
        int startIndex;
        if ((startIndex = fileName.lastIndexOf('.')) < 0){
            return "INVALID";
        }
        return fileName.substring(startIndex);
    }
    private static boolean isValidSongPath(Path songPath){
        String fileType = spliceFileType(songPath);
        if (fileType.equals("INVALID")){
            return false;
        }
        return VALID_SONG_PATHS.contains(fileType);
    }

    private static boolean isValidImgPath(Path imgPath){
        String fileType = spliceFileType(imgPath);
        if (fileType.equals("INVALID")){
            return false;
        }
        return VALID_IMAGE_PATHS.contains(fileType);
    }
    /**
     * Adds a new song to the Songs folder
     *
     * @param from the path to the song file
     * @return the new path to the song or null if exception caught
     */
    public static Path addSong(Path from) throws InvalidFileTypeException{
        if (!isValidSongPath(from)){
            throw new InvalidFileTypeException("Fail to load song path to db");
        }
        synchronized (SONGS_INDEX){
            try (FilesIndex songIndex = FilesIndex.of(SONGS_INDEX)){
                Path to = SONGS_FOLDER_PATH.resolve(Path.of("SONG_" + songIndex.getNextIndex() + spliceFileType(from)));
                Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
                return to;
            } catch (IOException | ClassNotFoundException e){
                e.fillInStackTrace();
                throw new RuntimeException("Could not add song");
            }
        }
    }

    /**
     * Adds a new image to the Images folder
     *
     * @param from path to the image file
     * @return the new path to the image or null if exception caught
     */
    public static Path addImg(Path from) throws InvalidFileTypeException{
        if (!isValidImgPath(from)){
            throw new InvalidFileTypeException("Fail to load song path to db");
        }
        synchronized (IMAGES_INDEX){
            try (FilesIndex imagesIndex = FilesIndex.of(IMAGES_INDEX)){
                Path to = IMAGES_FOLDER_PATH.resolve(Path.of("IMAGE_" + imagesIndex.getNextIndex() + spliceFileType(from)));
                Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
                return to;
            } catch (IOException | ClassNotFoundException e){
                e.fillInStackTrace();
                throw new RuntimeException("Failed to add image");
            }
        }
    }

    /**
     * Deletes song from Songs folder
     * @param path path to song file
     */
    public static void removeSongOrImg(Path path){
        try {
            Files.deleteIfExists(path);
        } catch (IOException e){
            e.fillInStackTrace();
            throw new RuntimeException("Failed to delete file");
        }
    }
}
