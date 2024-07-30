package main;

import api.DbRowToStructureApi;
import content.Content;
import db.DbManager;
import db.DbService;
import structures.Folder;
import structures.Playlist;

public class MusicManager {
    private Folder curFolder;
    private static final DbManager dbConn = DbService.DB_CONNECTION;

    /**
     * Initialize and organizes folders, playlist, and songs
     */
    public MusicManager(){
        this.curFolder = DbRowToStructureApi.toFolder(dbConn.getRootFolder());
    }

    /**
     * Changes working folder to a direct sub or parent folder
     */
    public void swapFolder(int folderId){
        for (Content content : curFolder.getContent()){
            if (content instanceof Folder folder && folder.getId() == folderId){
                curFolder = folder;
                return;
            }
        }
        throw new RuntimeException("Folder being swapped to does not exist");
    }

    public void swapPrevFolder(){
        if (curFolder.getParentFolder() == null){
            throw new RuntimeException("Folder does not have a parent");
        }
        curFolder = curFolder.getParentFolder();
    }

    public Playlist getPlaylist(int playlistId){
        for (Content content : curFolder.getContent()){
            if (content instanceof Playlist playlist && playlist.getId() == playlistId){
                return playlist;
            }
        }
        throw new RuntimeException("Folder does not have wanted playlist");
    }
}
