package database;

import music.Folder;
import music.Playlist;
import music.Song;

import java.util.List;

public class SQLiteManager extends DbManager{
    /**
     * Retrieves all folders
     *
     * @return all folders
     */
    @Override
    public List<Folder> getFolders() {
        return null;
    }

    /**
     * Retrieves a specific folder by its Id
     *
     * @param folderId Id of the folder
     * @return the folder with the specified Id
     */
    @Override
    public List<Folder> getFolders(int folderId) {
        return null;
    }

    /**
     * Retrieves playlists in a specific folder
     *
     * @param folderId id of the folder
     * @return playlists in the folder with specified folderId
     */
    @Override
    public List<Playlist> getPlaylists(int folderId) {
        return null;
    }

    /**
     * Retrieves the songs in a specific playlist.
     *
     * @param playlistId the id of the playlist
     * @return a list of songs in the specified playlist
     */
    @Override
    public List<Song> getSongs(int playlistId) {
        return null;
    }

    /**
     * Retrieves the image path for a specific image ID.
     *
     * @param imgId the ID of the image
     * @return the image path for the specified image ID
     */
    @Override
    public String getImgPath(int imgId) {
        return null;
    }

    /**
     * Retrieves the ID for the next song to be added.
     *
     * @return the ID for the next song to be added.
     */
    @Override
    public int getNextSongId() {
        return 0;
    }

    /**
     * Retrieves the ID for the next image to be added.
     *
     * @return the ID for the next image to be added.
     */
    @Override
    public int getNextImgId() {
        return 0;
    }

    /**
     * Retrieves the ID for the next Playlist to be added.
     *
     * @return the ID for the next Playlist to be added.
     */
    @Override
    public int getNextPlaylistId() {
        return 0;
    }

    /**
     * Retrieves the ID for the next folder to be added.
     *
     * @return the ID for the next folder to be added.
     */
    @Override
    public int getNextFolderId() {
        return 0;
    }

    /**
     * Adds a song to a playlist.
     *
     * @param songId     the ID of the song to be added
     * @param playlistId the ID of the playlist to which the song needs to be added
     */
    @Override
    public void addSongToPlaylist(int songId, int playlistId) {

    }

    /**
     * Inserts a new song into the database.
     *
     * @param newSong the song to be inserted
     */
    @Override
    public void insertNewSong(Song newSong) {

    }

    /**
     * Inserts a new playlist into the database.
     *
     * @param newPlaylist the playlist to be inserted
     */
    @Override
    public void insertNewPlaylist(Playlist newPlaylist) {

    }

    /**
     * Inserts a new folder into the database.
     *
     * @param newFolder the folder to be inserted
     */
    @Override
    public void insertNewFolder(Folder newFolder) {

    }

    /**
     * Deletes a song from a playlist.
     *
     * @param playlistId the ID of the playlist from which the song should be deleted
     * @param songId     the ID of the song to be deleted
     */
    @Override
    public void deleteSongFromPlaylist(int playlistId, int songId) {

    }

    /**
     * Deletes a song from the database.
     *
     * @param songId the ID of the song to be deleted
     */
    @Override
    public void deleteSong(int songId) {

    }

    /**
     * Deletes a playlist.
     *
     * @param playlistId the ID of the playlist to be deleted
     */
    @Override
    public void deletePlaylist(int playlistId) {

    }

    /**
     * Deletes a folder by its ID.
     *
     * @param folderId the ID of the folder to be deleted
     */
    @Override
    public void deleteFolder(int folderId) {

    }

    /**
     * Sets the ID for the next entity to be added in the database.
     *
     * @param idName the name of the ID field for the entity
     * @param nextId the ID value to be set
     */
    @Override
    public void setNextId(String idName, int nextId) {

    }

    /**
     * Sets the name of a song identified by its songId.
     *
     * @param songId   the ID of the song
     * @param songName the new name of the song
     */
    @Override
    public void setSongName(int songId, String songName) {

    }

    /**
     * Sets the name of a playlist identified by its playlistId.
     *
     * @param playlistId   the ID of the playlist
     * @param playlistName the new name of the playlist
     */
    @Override
    public void setPlaylistName(int playlistId, String playlistName) {

    }

    /**
     * Sets the name of a folder identified by its folderId.
     *
     * @param folderId   the ID of the folder
     * @param folderName the new name of the folder
     */
    @Override
    public void setFolderName(int folderId, String folderName) {

    }
}
