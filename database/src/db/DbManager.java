package db;
import records.FolderRow;
import records.SongRow;
import records.PlaylistRow;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


/**
 * Contains all the needed methods for a database manager
 * <p>
 * Main purpose is to make switching between SQLite and MySQL easier
 */
public abstract class DbManager implements Closeable {
    public static final String SONGS_FOLDER_PATH = "SongsFolder\\";
    public static final String IMAGES_FOLDER_PATH = "ImagesFolder\\";
    protected static final Map<Integer, String> imgMap = new HashMap<>();

    public static final DbManager DB_CONNECTION = new MySQLWrapper();
    private static class MySQLWrapper extends MySQLManager{}
    private static class SQLiteWrapper extends SQLiteManager{}

    
    public Map<Integer, String> getImgMap(){
        return new HashMap<>(imgMap);
    }
    /**
     * Retrieves all folders
     *
     * @return all folders
     */
    public abstract List<FolderRow> getFolders();

    /**
     * Retrieves a specific folder by its Id
     *
     * @param folderId Id of the folder
     * @return the folder with the specified Id
     */
    public abstract List<FolderRow> getFolders(int folderId);

    /**
     * Retrieves playlists in a specific folder
     *
     * @param folderId id of the folder
     * @return playlists in the folder with specified folderId
     */
    public abstract List<PlaylistRow> getPlaylists(int folderId);

    /**
     * Retrieves the songs in a specific playlist.
     *
     * @param playlistId the id of the playlist
     * @return a list of songs in the specified playlist
     */
    public abstract List<SongRow> getSongs(int playlistId);

    /**
     * Retrieves the image path for a specific image ID.
     *
     * @param imgId the ID of the image
     * @return the image path for the specified image ID
     */
    public abstract String getImgPath(int imgId);

    /**
     * Retrieves the default image path.
     *
     * @return the default image path as a {@code String}
     */
    public abstract String getDefaultImgPath();

    /**
     * Retrieves the ID for the next song to be added.
     *
     * @return the ID for the next song to be added.
     */
    public abstract int getNextSongId();
    /**
     * Retrieves the ID for the next image to be added.
     *
     * @return the ID for the next image to be added.
     */
    public abstract int getNextImgId();
    /**
     * Retrieves the ID for the next Playlist to be added.
     *
     * @return the ID for the next Playlist to be added.
     */
    public abstract int getNextPlaylistId();
    /**
     * Retrieves the ID for the next folder to be added.
     *
     * @return the ID for the next folder to be added.
     */
    public abstract int getNextFolderId();

    /**
     * Adds a song to a playlist.
     *
     * @param songId the ID of the song to be added
     * @param playlistId the ID of the playlist to which the song needs to be added
     */
    public abstract void addSongToPlaylist(int songId, int playlistId);

    /**
     * Inserts a new song into the database.
     *
     * @param newSong the song to be inserted
     */
    public abstract void insertNewSong(SongRow newSong);

    /**
     * Inserts a new playlist into the database.
     *
     * @param newPlaylist the playlist to be inserted
     */
    public abstract void insertNewPlaylist(PlaylistRow newPlaylist);

    /**
     * Inserts a new folder into the database.
     *
     * @param newFolder the folder to be inserted
     */
    public abstract void insertNewFolder(FolderRow newFolder);

    /**
     * Deletes a song from a playlist.
     *
     * @param playlistId the ID of the playlist from which the song should be deleted
     * @param songId     the ID of the song to be deleted
     */
    public abstract void deleteSongFromPlaylist(int playlistId, int songId);

    /**
     * Deletes a song from the database.
     *
     * @param songId the ID of the song to be deleted
     */
    public abstract void deleteSong(int songId);

    /**
     * Deletes a playlist.
     *
     * @param playlistId the ID of the playlist to be deleted
     */
    public abstract void deletePlaylist(int playlistId);

    /**
     * Deletes a folder by its ID.
     *
     * @param folderId the ID of the folder to be deleted
     */
    public abstract void deleteFolder(int folderId);

    /**
     * Sets the ID for the next entity to be added in the database.
     *
     * @param idName   the name of the ID field for the entity
     * @param nextId   the ID value to be set
     */
    public abstract void setNextId(String idName, int nextId);

    /**
     * Sets the name of a song identified by its songId.
     *
     * @param songId    the ID of the song
     * @param songName  the new name of the song
     */
    public abstract void setSongName(int songId, String songName);

    /**
     * Sets the name of a playlist identified by its playlistId.
     *
     * @param playlistId    the ID of the playlist
     * @param playlistName  the new name of the playlist
     */
    public abstract void setPlaylistName(int playlistId, String playlistName);

    /**
     * Sets the name of a folder identified by its folderId.
     *
     * @param folderId the ID of the folder
     * @param folderName the new name of the folder
     */
    public abstract void setFolderName(int folderId, String folderName);
}
