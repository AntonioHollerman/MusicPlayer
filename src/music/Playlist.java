package music;

import database.DbManager;
import main.Main;

import java.util.ArrayList;
import java.util.List;
public final class Playlist extends Content implements ContentContainer{
    private final List<Song> songList;
    private final int folderId;
    private DbManager dbConn = Main.DB_CONNECTION;


    /**
     * The Playlist class represents a playlist object that can contain songs.
     *
     * <p>
     * A playlist has a name, an image path, a playlist ID, and a folder ID. It can also contain an array of songs.
     */
    public Playlist(String playlistName, String imgPath, int playlistId, int folderId, List<Song> songList) {
        super(ContentType.PLAYLIST, imgPath, playlistName, playlistId);
        this.folderId = folderId;
        this.songList = songList;
    }
    public Playlist(String playlistName, int folderId){
        this(playlistName,
                Main.DB_CONNECTION.getDefaultImgPath(),
                Main.DB_CONNECTION.getNextPlaylistId(),
                folderId,
                new ArrayList<>());
    }

    /**
     * Retrieves the folder ID of the playlist.
     *
     * @return the folder ID of the playlist
     */
    public int getFolderId(){
        return folderId;
    }

    /**
     * Shuffles the songs in the playlist.
     *
     * <p>
     * This method shuffles the order of songs in the playlist by randomly rearranging them.
     * </p>
     *
     * <p>
     * This method does not return a value. It modifies the internal order of songs in the playlist.
     * </p>
     */
    public void shufflePlaylist(){

    }

    /**
     * Retrieves the current song "Playing" in the playlist.
     *
     * @return the current song in the playlist, or null if there is no current song
     */
    public Song getCurSong(){
        return null;
    }

    /**
     * Retrieves the next song in the playlist.
     *
     * @return the next song in the playlist, or null if there is no next song
     */
    public Song getNextSong(){
        return null;
    }

    /**
     * Returns the previous song in the playlist.
     *
     * @return the previous song in the playlist, or null if there is no previous song
     */
    public Song getPrevSong(){
        return null;
    }

    /**
     * Adds a new song to the playlist.
     *
     * @param newSong the new song to be added
     */
    public void addSong(Song newSong){

    }

    /**
     * Adds new songs to the playlist.
     *
     * @param newSongs the list of songs to be added
     */
    public void addSong(List<Song> newSongs){

    }

    /**
     * Starts playing the playlist from the specified song. If the songId is not found in the playlist,
     * the playlist will start from the first song.
     *
     * @param songId the ID of the song to start the playlist from
     */
    public void startPlaylist(int songId){

    }

    /**
     * Starts playing the playlist.
     *
     * <p>
     * This method starts playing the playlist from the current song. If no current song is set, it starts playing from the beginning of the playlist.
     * </p>
     *
     * <p>
     * This method does not return a value. It modifies the state of the playlist by setting the current song and starting playback.
     * </p>
     */
    public void startPlaylist(){

    }

    /**
     * Sets the title of the editable object
     *
     * @param Text replaces current playlist title
     */
    @Override
    public void setTitle(String Text) {

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

    /**
     * Retrieves the contents of the container.
     *
     * @return An array of Content objects representing the contents of the container.
     */
    @Override
    public Content[] getContent() {
        return songList.toArray(new Content[0]);
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
}
