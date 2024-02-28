public class Folder {
    private final int id;
    private String folderName;
    private Playlist[] playlistArr;
    private Folder[] folderArr;
    private Folder prevFolder;

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

    public Playlist[] getPlaylistArr() {
        return playlistArr;
    }

    public Folder[] getFolderArr() {
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
