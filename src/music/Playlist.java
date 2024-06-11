package music;

import java.util.List;
public final class Playlist implements Editable{
    private List<Song> songArr;
    private int curSongIndex;
    private String playlistName;
    private String imgPath;
    private final int playlistId;
    private final int folderId;

    /**
     * The Playlist class represents a playlist object that can contain songs.
     *
     * <p>
     * A playlist has a name, an image path, a playlist ID, and a folder ID. It can also contain an array of songs.
     */
    public Playlist(String playlistName, String imgPath, int playlistId, int folderId) {
        this.playlistName = playlistName;
        this.imgPath = imgPath;
        this.playlistId = playlistId;
        this.folderId = folderId;
    }

    /**
     * Returns the name of the playlist.
     *
     * @return the name of the playlist
     */
    public String getPlaylistName() {
        return playlistName;
    }

    /**
     * Retrieves the image path of the playlist or song.
     *
     * @return the image path of the playlist or song
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * Returns the playlist ID.
     *
     * @return the playlist ID
     */
    public int getPlaylistId() {
        return playlistId;
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
     * Retrieves the array of songs in the playlist.
     *
     * @return the array of songs in the playlist
     */
    public List<Song> getSongArr(){
        return songArr;
    }

    /**
     * Sets the name of the playlist.
     *
     * @param playlistName the new name of the playlist
     */
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    /**
     * Sets the image path of the playlist or song.
     *
     * @param imgPath the new image path to be set
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
}
