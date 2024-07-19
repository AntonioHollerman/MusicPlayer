package structures;

import content.Content;
import content.ContentType;
import db.DbManager;
import db.DbService;

import java.nio.file.Path;

public final class Song extends Content {
    private String songPath;
    private boolean isPlaying;
    private static final DbManager dbConn = DbService.DB_CONNECTION;

    /**
     * The Song class represents a song object that can be played.
     *
     * <p>
     * A song has a path to the audio file along with an optional image path, ID, and title.
     * </p>
     */
    public Song(String songPath, Path imgPath, String title, int id) {
        super(ContentType.SONG, imgPath, title, id);
        this.songPath = songPath;
        isPlaying = false;
    }
    public Song(String title, String songPath){
        this(songPath,
                dbConn.getDefaultImgPath(),
                title,
                dbConn.getNextSongId());
    }

    /**
     * Retrieves the path of the song.
     *
     * @return the path of the song
     */
    public String getSongPath() {
        return songPath;
    }


    /**
     * Returns the playing state of the song.
     *
     * @return true if the song is currently playing, false otherwise
     */
    public boolean isPlaying() {
        return isPlaying;
    }

    /**
     * Sets the path of the song.
     *
     * @param songPath the new path of the song
     */
    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }


    /**
     * Starts playing the song audio.
     * This method does not return a value.
     * It modifies the state of the song or playlist by starting playback.
     * </p>
     */
    public void play(){

    }

    /**
     * Stops playing the song audio.
     * This method does not return a value.
     * It modifies the state of the song or playlist by starting playback.
     * </p>
     */
    public void pause(){

    }

    /**
     * Deletes the editable object.
     *
     * <p> This method deletes the object, from the folder or playlist it is in.
     */
    @Override
    public void delete() {

    }


    /**
     * Sets the title for the content.
     *
     * @param newTitle the title the song changed to
     */
    @Override
    public void setTitle(String newTitle) {

    }

    /**
     * Sets the path to the content icon image.
     *
     * @param newIcon path to new icon
     * @return The new path to the content icon image.
     */
    @Override
    public String setIcon(String newIcon) {
        return "";
    }

}
