package music;

import database.DbManager;
import main.Main;

import java.util.ArrayList;
import java.util.List;

public final class Folder extends Content implements ContentContainer{
    private final List<Content> contentList;
    private final Folder prevFolder;
    private DbManager dbConn = Main.DB_CONNECTION;

    /**
     * The Folder class represents a folder that can contain playlists and sub-folders.
     */
    public Folder(int id, String folderName, String imgPath, Folder prevFolder, List<Content> contentList) {
        super(ContentType.FOLDER, imgPath, folderName, id);
        this.prevFolder = prevFolder;
        this.contentList = contentList;
    }
    public Folder(Folder prevFolder, String folderName){
        this(Main.DB_CONNECTION.getNextFolderId(),
                folderName,
                Main.DB_CONNECTION.getDefaultImgPath(),
                prevFolder,
                new ArrayList<>());
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
     * Sets the title of the editable object
     *
     * @param Text replaces current folder title.
     */
    @Override
    public void setTitle(String Text) {

    }

    /**
     * Sets the icon of the editable object.
     *
     * @param path the path to the icon file
     */
    @Override
    public void setIcon(String path) {

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
     * Sets the path to the content icon image.
     *
     * @return The new path to the content icon image.
     */
    @Override
    public String setIconPath() {
        return "";
    }

    /**
     * Sets the title for the content.
     */
    @Override
    public void setTitle() {

    }
}
