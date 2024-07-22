package structures;

import content.Content;
import content.ContentContainer;
import content.ContentType;
import db.DbManager;
import db.DbService;

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
    public Folder(int id, String folderName, Path imgPath, Folder prevFolder, List<Content> contentList) {
        super(ContentType.FOLDER, imgPath, folderName, id);
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
    public void createPlaylist(){

    }

    /**
     * Creates a new folder in the current folder.
     *
     * <p>
     * This method creates a new folder within the current folder. The new folder will be added
     * to the folderArr list of the current folder.
     * </p>
     */
    public void createFolder(){

    }

    /**
     * Removes a playlist from the current folder.
     *
     * @param id the ID of the playlist to be removed
     */
    public void removePlaylist(int id){

    }

    /**
     * Removes a folder with the given ID from the current folder.
     *
     * @param id the ID of the folder to be removed
     */
    public void removeFolder(int id){

    }

    /**
     * Deletes the editable object.
     *
     * <p> This method deletes the object, from the folder or playlist it is in.
     */
    @Override
    public void delete() {

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


    /**
     * Sets the title for the content.
     *
     * @param newTitle the title the song changed to
     */
    @Override
    public void setTitle(String newTitle) {

    }

    /**
     * Add icon to file system then make icon the result of new Path
     *
     * @param newIcon path to new icon
     * @return The new path to the content icon image.
     */
    @Override
    public Path setIcon(Path newIcon) {
        int imgId = dbConn.insertNewImage(newIcon);
        this.iconPath = dbConn.getImgPath(imgId);
        dbConn.setFolderImg(getId(), imgId);
        return this.iconPath;
    }

    @Override
    public Path setIcon(int imgId) {
        this.iconPath = dbConn.getImgPath(imgId);
        dbConn.setFolderImg(getId(), imgId);
        return dbConn.getImgPath(imgId);
    }
}
