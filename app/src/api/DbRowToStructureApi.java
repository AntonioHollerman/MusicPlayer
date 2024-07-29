package api;

import content.Content;
import db.DbManager;
import db.DbService;
import records.FolderRow;
import records.PlaylistRow;
import records.SongRow;
import structures.Folder;
import structures.Playlist;
import structures.Song;

import java.util.ArrayList;
import java.util.List;

public class DbRowToStructureApi {
    private static final DbManager dbConn = DbService.DB_CONNECTION;

    public static Song toSong(SongRow row){
        return new Song(
                row.songPath(),
                row.imgId(),
                row.title(),
                row.id()
        );
    }

    public static Playlist toPlaylist(PlaylistRow row){
        return new Playlist(
                row.playlistName(),
                row.imgId(),
                row.id(),
                row.folderId(),
                getPlaylistSongs(row.id())
        );
    }

    private static List<Song> getPlaylistSongs(int playlistId){
        List<Song> songsConverted = new ArrayList<>();
        for (SongRow row : dbConn.getSongs(playlistId)){
            songsConverted.add(toSong(row));
        }
        return songsConverted;
    }

    public static Folder toFolder(FolderRow row){
        return toFolder(row, null);
    }

    private static Folder toFolder(FolderRow folderRow, Folder parent){
        Folder currentFolder = new Folder(
                folderRow.id(),
                folderRow.folderName(),
                folderRow.imgId(),
                parent
        );

        for (PlaylistRow row : dbConn.getPlaylists(folderRow.id())){
            currentFolder.addPlaylist(toPlaylist(row));
        }

        for (FolderRow row : dbConn.getFolders(folderRow.id())){
            currentFolder.addFolder(toFolder(row, currentFolder));
        }
        return currentFolder;
    }


}
