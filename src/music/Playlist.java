package music;

import java.util.List;
public class Playlist implements Editable{
    private List<Song> songArr;
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
    public List<Song> getSongArr(){
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
    public void addSong(List<Song> newSongs){

    }
    public void startPlaylist(int songId){

    }
    public void startPlaylist(){

    }

    /**
     * Sets the title of the editable object
     *
     * @param Text replaces current playlist title
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
