package structures;

import content.Content;
import content.ContentContainer;
import content.ContentType;
import db.DbManager;
import db.DbService;
import records.FolderRow;
import records.PlaylistRow;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class Folder extends Content implements ContentContainer {
    private final List<Content> contentList;
    private final Folder prevFolder;
    private static final DbManager dbConn = DbService.DB_CONNECTION;

    /**
     * The Folder class represents a folder that can contain playlists and sub-folders.
     */
    public Folder(int id, String folderName, int iconId, Folder prevFolder, List<Content> contentList) {
        super(ContentType.FOLDER, iconId, folderName, id);
        this.prevFolder = prevFolder;
        this.contentList = contentList;
    }

    /**
     * Returns the parent folder of the current folder.
     *
     * @return the parent folder of the current folder
     */
    public Folder getParentFolder() {
        return prevFolder;
    }


    /**
     * Creates a new playlist in the current folder.
     *
     * <p> The playlist is added to the playlistArr list of the current folder.
     */
    public void addPlaylist(String playlistName, Path imgPath, List<Content> contentList){
        int imgId = dbConn.insertNewImage(imgPath);
        contentList.add(
                new Playlist(
                        playlistName,
                        imgId,
                        dbConn.insertNewPlaylist(new PlaylistRow(
                                -1,
                                getId(),
                                imgId,
                                playlistName
                        )),
                        getId()
                )
        );
    }

    /**
     * Creates a new playlist in the current folder.
     *
     * <p> The playlist is added to the playlistArr list of the current folder.
     */
    public void addPlaylist(String playlistName, int imgId, List<Content> contentList){
        contentList.add(
                new Playlist(
                        playlistName,
                        imgId,
                        dbConn.insertNewPlaylist(new PlaylistRow(
                                -1,
                                getId(),
                                imgId,
                                playlistName
                        )),
                        getId()
                )
        );
    }

    public void addPlaylist(Playlist playlist){
        if (playlist.getFolderId() != this.getId()){
            throw new RuntimeException("Playlist being added is not a child of folder to be added to");
        }

        contentList.add(playlist);
    }

    /**
     * Adds the provided folder in the current folder. Create new folder and image row in database
     *
     * <p>
     * This method adds a new folder within the current folder. The new folder will be added
     * to the folderArr list of the current folder.
     * </p>
     */
    public void addFolder(String folderName, Path imgPath, List<Content> contentList){
        int imgId = dbConn.insertNewImage(imgPath);
        contentList.add(new Folder(
                dbConn.insertNewFolder(new FolderRow(
                        -1,
                        getId(),
                        imgId,
                        folderName
                )),
                folderName,
                imgId,
                this,
                new ArrayList<>(contentList)
        ));
    }

    /**
     * Adds the provided folder in the current folder. Create new folder in database.
     *
     * <p>
     * This method adds a new folder within the current folder. The new folder will be added
     * to the folderArr list of the current folder.
     * </p>
     */
    public void addFolder(String folderName, int imgId, List<Content> contentList){
        contentList.add(new Folder(
                dbConn.insertNewFolder(new FolderRow(
                        -1,
                        getId(),
                        imgId,
                        folderName
                )),
                folderName,
                imgId,
                this,
                new ArrayList<>(contentList)
        ));
    }

    /**
     * Adds folder to working folder without affecting database
     * @param folder folder to be added
     */
    public void addFolder(Folder folder){
        // making sure this is the folder parent
        if (this.getId() != folder.getParentFolder().getId()){
            throw new RuntimeException("Folder being added is not a child of folder to be added to");
        }

        contentList.add(folder);
    }

    /**
     * Removes a playlist from the current folder.
     *
     * @param id the ID of the playlist to be removed
     */
    public void removePlaylist(int id){
        for (int i = 0; i < contentList.size(); i ++){
            Content content = contentList.get(i);
            if (content.getType() == ContentType.PLAYLIST && content.getId() == id){
                dbConn.deletePlaylist(id);
                contentList.remove(i);
                break;
            }
        }
    }

    /**
     * Removes a folder with the given ID from the current folder.
     *
     * @param id the ID of the folder to be removed
     */
    public void removeFolder(int id){
        for (int i = 0; i < contentList.size(); i ++){
            Content content = contentList.get(i);
            if (content.getType() == ContentType.FOLDER && content.getId() == id){
                dbConn.deleteFolder(id);
                contentList.remove(i);
                break;
            }
        }
    }

    /**
     * Deletes the editable object.
     *
     * <p> This method deletes the object, from the folder or playlist it is in.
     */
    @Override
    public void delete() {
        dbConn.deleteFolder(getId());
    }

    /**
     * Retrieves the contents of the container.
     *
     * @return An array of Content objects representing the contents of the container.
     */
    @Override
    public Content[] getContent() {
        return contentList.toArray(new Content[0]);
    }
}
