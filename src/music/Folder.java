package music;

import java.util.List;
public final class Folder implements Editable{
    private final int id;
    private String folderName;
    private List<Playlist> playlistArr;
    private List<Folder> folderArr;
    private final Folder prevFolder;

    public Folder(int id, String folderName, Folder prevFolder) {
        this.id = id;
        this.folderName = folderName;
        this.prevFolder = prevFolder;
    }
    public Folder(int id, String folderName){
        this(id, folderName, null);
    }

    public int getId() {
        return id;
    }

    public String getFolderName() {
        return folderName;
    }

    public List<Playlist> getPlaylistArr() {
        return playlistArr;
    }

    public List<Folder> getFolderArr() {
        return folderArr;
    }

    public Folder getPrevFolder() {
        return prevFolder;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
    public void createPlaylist(){

    }
    public void createFolder(){

    }
    public void removePlaylist(){

    }
    public void removeFolder(){

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
}
