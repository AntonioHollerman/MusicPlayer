import java.util.ArrayList;
public class Playlist {
    private ArrayList<Song> songArr = new ArrayList<>();
    private int curSongIndex;
    private String playlistName;
    private String imgPath;
    private final int playlistId;
    private final int folderId;

    public Playlist(String playlistName, String imgPath, int playlistId, int folderId) {
        this.playlistName = playlistName;
        this.imgPath = imgPath;
        this.playlistId = playlistId;
        this.folderId = folderId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public int getPlaylistId() {
        return playlistId;
    }
    public int getFolderId(){
        return folderId;
    }
    public ArrayList<Song> getSongArr(){
        return songArr;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public void shufflePlaylist(){

    }
    public Song getCurSong(){
        return null;
    }
    public Song getNextSong(){
        return null;
    }
    public Song getPrevSong(){
        return null;
    }
    public void addSong(Song newSong){

    }
    public void addSong(ArrayList<Song> newSongs){

    }
    public void startPlaylist(int songId){

    }
    public void startPlaylist(){

    }
}
