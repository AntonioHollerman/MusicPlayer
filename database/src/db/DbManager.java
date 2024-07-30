package db;


import records.*;

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
        CREATE TABLE IF NOT EXISTS songs_mapped(
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
     * Extract song by id
     * @param songId id of song
     * @return row of the song extracted
     */
    public SongRow getSong(int songId){
        String sql = "SELECT * FROM songs WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, songId);
            return DbToRecordAPI.toSongs(ps.executeQuery()).get(0);
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to get song row");
        }
    }

    /**
     * Extracts playlist with id
     * @param playlistId id of playlist
     * @return row of extracted playlist
     */
    public PlaylistRow getPlaylist(int playlistId){
        String sql = "SELECT * FROM playlists WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, playlistId);
            return DbToRecordAPI.toPlaylists(ps.executeQuery()).get(0);
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to get playlist row");
        }
    }

    public FolderRow getRootFolder(){
        String sql = "SELECT * FROM FOLDERS WHERE folder_name = 'ROOT'";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            return DbToRecordAPI.toFolders(ps.executeQuery()).get(0);
        } catch (IndexOutOfBoundsException | SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to get root folder");
        }
    }
    /**
     * Extracts folder from id
     * @param folderId id of folder
     * @return row of folder extracted
     */
    public FolderRow getFolder(int folderId){
        String sql = "SELECT * FROM folders WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, folderId);
            return DbToRecordAPI.toFolders(ps.executeQuery()).get(0);
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to get folder row");
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
                INNER JOIN songs_mapped AS sm ON s.id = sm.song_id
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
            } else if (imgId == getDefaultImgId()) {
                throw new SQLException("Image id does not exist");
            } else {
                throw new SQLException("No image path for give id: " + imgId);
            }
            rs.close();
            if (!Files.exists(path)){
                return getImgPath(getDefaultImgId());
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
     * @return the default image path as a Path
     */
    
    public int getDefaultImgId() {
        return 0;
    }

    /**
     *  Retrieves song path from id
     * @param songId id of song path to extract
     * @return song path from id
     */
    public Path getSongPath(int songId){
        String sql = "SELECT song_path FROM songs WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, songId);
            ResultSet rs = ps.getResultSet();
            rs.next();

            return Path.of(rs.getString("song_path"));
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to insert folder");
        }
    }

    public int getNewestId(String table){
        String sql = "SELECT MAX(id) as max_id FROM ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, table);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("max_id") : 0;
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Failed to get newest id");
        }
    }


    /**
     * Adds a song to a playlist.
     *
     * @param songId     the ID of the song to be added
     * @param playlistId the ID of the playlist to which the song needs to be added
     */
    public void addSongToPlaylist(int songId, int playlistId) {
        String sql = """
                INSERT INTO songs_mapped (song_id, playlist_id)
                VALUES (?, ?)""";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, songId);
            ps.setInt(2, playlistId);

            ps.executeUpdate(sql);
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to add song to playlist");
        }
    }

    /**
     * Adds image to database and file system
     * @param imgPath path to be added
     * @return the id of added image
     */
    public int insertNewImage(Path imgPath){
        String sql = """
                INSERT INTO images (image_path)
                VALUES (?)
                RETURNING id;""";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, FilesManager.addImg(imgPath).toString());
            ps.execute();

            ResultSet rs = ps.getResultSet();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to add image");
        }
    }

    /**
     * Inserts a new song into the database.
     *
     * @param title the song to be inserted
     * @param songPath path of song to be added
     * @return the id for song added
     */
    public int insertNewSong(String title, Path songPath, int imgId) {
        String sql = """
                INSERT INTO songs (title, song_path, img_id)
                VALUES (?, ?, ?)
                RETURNING id;""";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, title);
            ps.setString(2, songPath.toString());
            ps.setInt(3, imgId < 0 ? getDefaultImgId() : imgId);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to add song");
        }
    }

    /**
     * Inserts a new playlist into the database.
     *
     * @param newPlaylist the playlist to be inserted
     * @return the id for the playlist added
     */
    
    public int insertNewPlaylist(PlaylistRow newPlaylist) {
        String sql = """
                INSERT INTO playlists (folder_id, img_id, playlist_name)
                VALUES (?, ?, ?)
                RETURNING id;""";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, newPlaylist.folderId());
            ps.setInt(2, newPlaylist.imgId());
            ps.setString(3, newPlaylist.playlistName());

            ps.execute();
            ResultSet rs = ps.getResultSet();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException e) {
            e.fillInStackTrace();
            throw new RuntimeException("Fail to insert playlist");
        }
    }

    /**
     * Inserts a new folder into the database.
     *
     * @param newFolder the folder to be inserted
     */
    
    public int insertNewFolder(FolderRow newFolder) {
        String sql = """
                INSERT INTO folders (parent_id, img_id, folder_name)
                VALUES (?, ?, ?)
                RETURNING id;""";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, newFolder.parentId());
            ps.setInt(2, newFolder.imgId() < 0 ? getDefaultImgId() : newFolder.imgId());
            ps.setString(3, newFolder.folderName());

            ps.execute();
            ResultSet rs = ps.getResultSet();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to insert folder");
        }
    }

    /**
     * Deletes a song from a playlist.
     *
     * @param songId id for song to be removed
     * @param playlistId id of playlist to remove from
     */
    
    public void deleteSongFromPlaylist(int songId, int playlistId) {
        String sql = """
                WITH cte AS (
                    SELECT id
                    FROM songs_mapped
                    WHERE song_id = ? AND playlist_id = ?
                    ORDER BY id
                    LIMIT 1
                )
                DELETE FROM your_table
                WHERE id IN (SELECT id FROM cte);""";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, songId);
            ps.setInt(2, playlistId);
            ps.executeUpdate();
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to delete song from playlist");
        }
    }

    /**
     *
     * @param imageId id for image to delete
     */
    public void deleteImage(int imageId){
        String sql = "DELETE FROM images WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, imageId);
            ps.executeUpdate();

            Path imagePath = getImgPath(imageId);
            FilesManager.removeSongOrImg(imagePath);
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to delete iamge");
        }
    }
    /**
     * Deletes a song from the database.
     *
     * @param songId the ID of the song to be deleted
     */
    public void deleteSong(int songId) {
        String sql = """
            DELETE FROM songs WHERE id = ?;
            DELETE FROM songs_mapped WHERE song_id = ?;""";
        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, songId);
            ps.setInt(2, songId);
            ps.executeUpdate();

            Path songPath = getSongPath(songId);
            FilesManager.removeSongOrImg(songPath);

        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to delete song from database");
        }
    }

    /**
     * Deletes a playlist.
     *
     * @param playlistId the ID of the playlist to be deleted
     */
    public void deletePlaylist(int playlistId) {
        String sql = """
                DELETE FROM playlists WHERE id = ?;
                DELETE FROM songs_mapped WHERE playlist_id = ?;""";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, playlistId);
            ps.setInt(2, playlistId);
            ps.executeUpdate();
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to remove playlist");
        }
    }

    /**
     * Deletes a folder by its ID.
     *
     * @param folderId the ID of the folder to be deleted
     */
    
    public void deleteFolder(int folderId) {
        for (PlaylistRow row : getPlaylists(folderId)){
            deletePlaylist(row.id());
        }

        String sql = "DELETE FROM playlists WHERE id = ?;";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, folderId);
            ps.executeUpdate();
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to delete folder");
        }

        for (FolderRow row : getFolders(folderId)){
            deleteFolder(row.id());
        }
    }

    public void setSongImg(int songId, int imgId){
        String sql = "UPDATE songs SET img_id = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, songId);
            ps.setInt(2, imgId);

            ps.executeUpdate();
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to set song image");
        }
    }

    public void setPlaylistImg(int playlistId, int imgId){
        String sql = "UPDATE playlists SET img_id = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, playlistId);
            ps.setInt(2, imgId);

            ps.executeUpdate();
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to set song image");
        }
    }

    public void setFolderImg(int folderId, int imgId){
        String sql = "UPDATE folders SET img_id = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, folderId);
            ps.setInt(2, imgId);

            ps.executeUpdate();
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to set song image");
        }
    }
    /**
     * Sets the name of a song identified by its songId.
     *
     * @param songId   the ID of the song
     * @param songName the new name of the song
     */
    public void setSongName(int songId, String songName) {
        String sql = "UPDATE songs SET title = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, songName);
            ps.setInt(2, songId);

            ps.executeUpdate();
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to update song name");
        }
    }

    /**
     * Sets the name of a playlist identified by its playlistId.
     *
     * @param playlistId   the ID of the playlist
     * @param playlistName the new name of the playlist
     */
    public void setPlaylistName(int playlistId, String playlistName) {
        String sql = "UPDATE playlists SET playlist_name = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, playlistName);
            ps.setInt(2, playlistId);

            ps.executeUpdate();
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to update song name");
        }
    }

    /**
     * Sets the name of a folder identified by its folderId.
     *
     * @param folderId   the ID of the folder
     * @param folderName the new name of the folder
     */
    
    public void setFolderName(int folderId, String folderName) {
        String sql = "UPDATE folders SET folder_name = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, folderName);
            ps.setInt(2, folderId);

            ps.executeUpdate();
        } catch (SQLException e){
            e.fillInStackTrace();
            throw new RuntimeException("Fail to update song name");
        }
    }


    
    public void close() throws IOException {
        try {
            conn.close();
        } catch (SQLException e){
            e.fillInStackTrace();
        }
    }
}
