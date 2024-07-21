package records;

import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbToRecordAPI {
    public static List<FolderRow> toFolders(ResultSet rs) throws SQLException {
        List<FolderRow> rows = new ArrayList<>();
        try (rs){
            while (rs.next()){
                rows.add(
                        new FolderRow(
                                rs.getInt("id"),
                                rs.getInt("parent_id"),
                                rs.getInt("img_id"),
                                rs.getString("folder_name")
                        )
                );
            }
        }
        return rows;
    }
    public static List<ImageRow> toImages(ResultSet rs) throws SQLException{
        List<ImageRow> rows = new ArrayList<>();
        try (rs){
            while (rs.next()){
                rows.add(
                        new ImageRow(
                                rs.getInt("id"),
                                Path.of(rs.getString("img_path"))
                        )
                );
            }
        }
        return rows;
    }
    public static List<PlaylistRow> toPlaylists(ResultSet rs) throws SQLException{
        List<PlaylistRow> rows = new ArrayList<>();
        try (rs){
            while (rs.next()){
                rows.add(
                        new PlaylistRow(
                                rs.getInt("id"),
                                rs.getInt("folder_id"),
                                rs.getInt("img_id"),
                                rs.getString("playlist_name")
                        )
                );
            }
        }
        return rows;
    }
    public static List<SongRow> toSongs(ResultSet rs) throws SQLException{
        List<SongRow> rows = new ArrayList<>();
        try (rs){
            while (rs.next()){
                rows.add(
                        new SongRow(
                                rs.getInt("id"),
                                rs.getInt("img_id"),
                                rs.getString("title"),
                                Path.of(rs.getString("song_path"))
                        )
                );
            }
        }
        return rows;
    }
    public static List<SongsMappedRow> toMappedSongs(ResultSet rs) throws SQLException{
        List<SongsMappedRow> rows = new ArrayList<>();
        try (rs){
            while (rs.next()){
                rows.add(
                        new SongsMappedRow(
                                rs.getInt("song_id"),
                                rs.getInt("playlist_id")
                        )
                );
            }
        }
        return rows;
    }
}
