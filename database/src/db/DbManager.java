package db;


import records.DbToRecordAPI;
import records.FolderRow;
import records.PlaylistRow;
import records.SongRow;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.util.List;
import java.nio.file.Path;

/**
 * The MySQLManager class extends the DbManager class and provides methods for interacting with and connecting to
 * a SQLite database.
 */
public class DbManager {
    protected final Connection conn;

    protected DbManager(Connection conn) {
        this.conn = conn;
    }

    /**
     * Initialize database with tables
     */
    
    public void createTables() {
        String tables = """
        CREATE TABLE IF NOT EXISTS images(
            id INTEGER PRIMARY KEY,
            img_path TEXT
        );
        CREATE TABLE IF NOT EXISTS songs(
            id INTEGER PRIMARY KEY,
            img_id INTEGER,
            title TEXT,
            song_path TEXT,
            FOREIGN KEY (img_id) REFERENCES images(id)
        );
        CREATE TABLE IF NOT EXISTS folders(
            id INTEGER PRIMARY KEY,
            parent_id INTEGER,
            img_id INTEGER,
            folder_name TEXT,
            FOREIGN KEY (parent_id) REFERENCES folders(id),
            FOREIGN KEY (img_id) REFERENCES images(id)
        );
        CREATE TABLE IF NOT EXISTS playlists(
            id INTEGER PRIMARY KEY,
            folder_id INTEGER,
            img_id INTEGER,
            playlist_name TEXT,
            FOREIGN KEY (folder_id) REFERENCES folders(id),
            FOREIGN KEY (img_id) REFERENCES images(id)
        );
        CREATE TABLE IF NOT EXISTS song_mapped(
            song_id INTEGER,
            playlist_id INTEGER,
            FOREIGN KEY (song_id) REFERENCES songs(id),
            FOREIGN KEY (playlist_id) REFERENCES playlists(id)
        );""";
        try (PreparedStatement ps = conn.prepareStatement(tables)){
            ps.execute();
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Failed to create tables");
        }
    }

    /**
     * Retrieves all folders
     *
     * @return all folders
     */
    
    public List<FolderRow> getFolders() {
        String sql = "SELECT * FROM folders";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            return DbToRecordAPI.toFolders(ps.executeQuery());
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to get all folders");
        }
    }

    /**
     * Retrieves folders of a parent folder by the parent Id
     *
     * @param parentId Id of the folder
     * @return the folder with the specified Id
     */
    
    public List<FolderRow> getFolders(int parentId) {
        String sql = "SELECT * FROM folders WHERE parent_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, parentId);
            return DbToRecordAPI.toFolders(ps.executeQuery());
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to get all folders");
        }
    }

    /**
     * Retrieves playlists in a specific folder
     *
     * @param parentId id of the folder
     * @return playlists in the folder with specified folderId
     */
    
    public List<PlaylistRow> getPlaylists(int parentId) {
        String sql = "SELECT * FROM playlists WHERE folder_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, parentId);
            return DbToRecordAPI.toPlaylists(ps.executeQuery());
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to retrieve playlists");
        }
    }

    /**
     * Retrieves the songs in a specific playlist.
     *
     * @param playlistId the id of the playlist
     * @return a list of songs in the specified playlist
     */
    
    public List<SongRow> getSongs(int playlistId) {
        String sql = """
                SELECT id, title, song_path, img_id FROM songs AS s
                INNER JOIN song_mapped AS sm ON s.id = sm.song_id
                WHERE sm.playlist_id = ?""";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, playlistId);
            return DbToRecordAPI.toSongs(ps.executeQuery());
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to map songs");
        }
    }

    /**
     * Retrieves the image path for a specific image ID.
     *
     * @param imgId the ID of the image
     * @return the image path for the specified image ID
     */
    
    public Path getImgPath(int imgId) {
        String sql = "SELECT img_path FROM images WHERE id = ?";
        Path path;
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, imgId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                path = Path.of(rs.getString("img_path"));
            } else {
                throw new SQLException("No image path for give id: " + imgId);
            }

            if (!Files.exists(path)){
                return getDefaultImgPath();
            } else {
                return path;
            }
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Failed to get image path");
        }
    }

    /**
     * Retrieves the default image path.
     *
     * @return the default image path as a {@code String}
     */
    
    public Path getDefaultImgPath() {
        return null;
    }

    /**
     * Retrieves the ID for the next song to be added.
     *
     * @return the ID for the next song to be added.
     */
    
    public int getNextSongId() {
        return 0;
    }

    /**
     * Retrieves the ID for the next image to be added.
     *
     * @return the ID for the next image to be added.
     */
    
    public int getNextImgId() {
        return 0;
    }

    /**
     * Retrieves the ID for the next Playlist to be added.
     *
     * @return the ID for the next Playlist to be added.
     */
    
    public int getNextPlaylistId() {
        return 0;
    }

    /**
     * Retrieves the ID for the next folder to be added.
     *
     * @return the ID for the next folder to be added.
     */
    
    public int getNextFolderId() {
        return 0;
    }

    /**
     * Adds a song to a playlist.
     *
     * @param songId     the ID of the song to be added
     * @param playlistId the ID of the playlist to which the song needs to be added
     */
    
    public void addSongToPlaylist(int songId, int playlistId) {

    }

    /**
     * Inserts a new song into the database.
     *
     * @param newSong the song to be inserted
     */
    
    public void insertNewSong(SongRow newSong) {

    }

    /**
     * Inserts a new playlist into the database.
     *
     * @param newPlaylist the playlist to be inserted
     */
    
    public void insertNewPlaylist(PlaylistRow newPlaylist) {

    }

    /**
     * Inserts a new folder into the database.
     *
     * @param newFolder the folder to be inserted
     */
    
    public void insertNewFolder(FolderRow newFolder) {

    }

    /**
     * Deletes a song from a playlist.
     *
     * @param playlistId the ID of the playlist from which the song should be deleted
     * @param songId     the ID of the song to be deleted
     */
    
    public void deleteSongFromPlaylist(int playlistId, int songId) {

    }

    /**
     * Deletes a song from the database.
     *
     * @param songId the ID of the song to be deleted
     */
    
    public void deleteSong(int songId) {

    }

    /**
     * Deletes a playlist.
     *
     * @param playlistId the ID of the playlist to be deleted
     */
    
    public void deletePlaylist(int playlistId) {

    }

    /**
     * Deletes a folder by its ID.
     *
     * @param folderId the ID of the folder to be deleted
     */
    
    public void deleteFolder(int folderId) {

    }

    /**
     * Sets the ID for the next entity to be added in the database.
     *
     * @param idName the name of the ID field for the entity
     * @param nextId the ID value to be set
     */
    
    public void setNextId(String idName, int nextId) {

    }

    /**
     * Sets the name of a song identified by its songId.
     *
     * @param songId   the ID of the song
     * @param songName the new name of the song
     */
    
    public void setSongName(int songId, String songName) {

    }

    /**
     * Sets the name of a playlist identified by its playlistId.
     *
     * @param playlistId   the ID of the playlist
     * @param playlistName the new name of the playlist
     */
    
    public void setPlaylistName(int playlistId, String playlistName) {

    }

    /**
     * Sets the name of a folder identified by its folderId.
     *
     * @param folderId   the ID of the folder
     * @param folderName the new name of the folder
     */
    
    public void setFolderName(int folderId, String folderName) {

    }


    
    public void close() throws IOException {
        try {
            conn.close();
        } catch (SQLException e){
            e.fillInStackTrace();
        }
    }
}
