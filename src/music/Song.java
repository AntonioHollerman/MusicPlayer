package music;

import database.DbManager;
import main.Main;

public final class Song extends Content{
    private String songPath;
    private boolean isPlaying;
    private DbManager dbConn = Main.DB_CONNECTION;

    /**
     * The Song class represents a song object that can be played.
     *
     * <p>
     * A song has a path to the audio file along with an optional image path, ID, and title.
     * </p>
     */
    public Song(String songPath, String imgPath, String title, int id) {
        super(ContentType.SONG, imgPath, title, id);
        this.songPath = songPath;
        isPlaying = false;
    }
    public Song(String title, String songPath){
        this(songPath,
                Main.DB_CONNECTION.getDefaultImgPath(),
                title,
                Main.DB_CONNECTION.getNextSongId());
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
     * Sets the path to the content icon image.
     *
     * @return The new path to the content icon image.
     */
    @Override
    public String setIconPath() {
        return "";
    }

    /**
     * Sets the title for the content.
     */
    @Override
    public void setTitle() {

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
     * Sets the image path of the song or playlist.
     *
     * @param imgPath the new image path to be set
     */
    public void setImgPath(String imgPath) {

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
     * Sets the title of the song.
     *
     * @param title the new title of the song
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the icon of the editable object.
     *
     * @param path the path to the icon file
     */
    @Override
    public void setIcon(String path) {

    }

    /**
     * Deletes the editable object.
     *
     * <p> This method deletes the object, from the folder or playlist it is in.
     */
    @Override
    public void delete() {

    }
}
