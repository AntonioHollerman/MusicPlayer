package music;

import java.util.List;
public class Folder {
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
    public void deletePlaylist(){

    }
    public void deleteFolder(){

    }
    
}
