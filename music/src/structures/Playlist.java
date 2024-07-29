package structures;

import content.Content;
import content.ContentContainer;
import content.ContentType;
import db.DbManager;
import db.DbService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public final class Playlist extends Content implements ContentContainer {
    private final List<Song> songArr;
    private List<Song> activeArr;
    private int curSongIndex;
    private final int folderId;
    private boolean isActive;
    private static final DbManager dbConn = DbService.DB_CONNECTION;

    /**
     * The Playlist class represents a playlist object that can contain songs.
     *
     * <p>
     * A playlist has a name, an image path, a playlist ID, and a folder ID. It can also contain an array of songs.
     */
    public Playlist(String playlistName, int iconId, int playlistId, int folderId){
        this(playlistName, iconId, playlistId, folderId, new ArrayList<>());
    }

    public Playlist(String playlistName, int iconId, int playlistId, int folderId, List<Song> songArr){
        super(ContentType.PLAYLIST, iconId, playlistName, playlistId);
        this.folderId = folderId;
        this.curSongIndex = 0;
        this.songArr = songArr;
        this.isActive = false;
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
    public Song[] getSongArr(){
        return songArr.toArray(Song[]::new);
    }

    /**
     * Accesses the active playlist of songs
     * @return active list of songs, could be null if not activated properly
     */
    public List<Song> getActiveArr(){
        return activeArr;
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
        if (activeArr.isEmpty()){
            return;
        }
        List<Song> arrShuffled = new ArrayList<>(activeArr);
        arrShuffled.remove(curSongIndex);
        Collections.shuffle(arrShuffled);

        arrShuffled.add(0, activeArr.get(curSongIndex));
        curSongIndex = 0;
        activeArr = arrShuffled;
    }

    /**
     * Retrieves the current song "Playing" in the playlist.
     *
     * @return the current song in the playlist, or null if there is no current song
     */
    public Song getCurSong(){
        if (isActive && !activeArr.isEmpty()){
            return activeArr.get(curSongIndex);
        }
        return null;
    }

    /**
     * Retrieves the next song in the playlist.
     *
     * @return the next song in the playlist, or null if there is no next song
     */
    public Song goNextSong(){
        if (activeArr.isEmpty()){
            return null;
        } else if (activeArr.size() == 1){
            return activeArr.get(0);
        } else {
            curSongIndex = activeArr.size() - 1 == curSongIndex ? 0 : curSongIndex + 1;
            return activeArr.get(curSongIndex);
        }
    }

    /**
     * Returns the previous song in the playlist.
     *
     * @return the previous song in the playlist, or null if there is no previous song
     */
    public Song goPrevSong(){
        if (activeArr.isEmpty()){
            return null;
        } else if (activeArr.size() == 1){
            return activeArr.get(0);
        } else {
            curSongIndex = curSongIndex == 0 ? activeArr.size() - 1 : curSongIndex - 1;
            return activeArr.get(curSongIndex);
        }
    }

    /**
     * Adds a new song to the playlist.
     *
     * @param newSong the new song to be added
     */
    public void addSong(Song newSong){
        songArr.add(newSong);
        dbConn.addSongToPlaylist(newSong.getId(), folderId);
    }

    /**
     * Adds new songs to the playlist.
     *
     * @param newSongs the list of songs to be added
     */
    public void addSong(Song... newSongs){
        for (Song song : newSongs){
            addSong(song);
        }
    }

    /**
     * Remove song from Playlist structure and database
     * @param songId id of song to be removed
     */
    public void removeSong(int songId){
        for (int i = 0; i < songArr.size(); i++){
            Song song = songArr.get(i);
            if (song.getId() == songId){
                dbConn.deleteSongFromPlaylist(songId, getId());
                songArr.remove(i);
                break;
            }
        }
    }

    /**
     * Starts playing the playlist from the specified song. If the songId is not found in the playlist,
     * the playlist will start from the first song.
     *
     * @param songIndex the index of the song to start the playlist from
     */
    public Song startPlaylist(int songIndex){
        curSongIndex = songIndex;
        isActive = true;
        activeArr = new ArrayList<>(songArr);
        return songArr.get(songIndex);
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
    public Song startPlaylist(){
        return startPlaylist(0);
    }

    public void stopPlaylist(){
        isActive = false;
        activeArr = null;
    }

    /**
     * Retrieves the contents of the container.
     *
     * @return An array of Content objects representing the contents of the container.
     */
    @Override
    public Content[] getContent() {
        return songArr.toArray(new Song[0]);
    }
}
