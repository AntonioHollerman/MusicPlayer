public class Playlist {
    private Song[] songArr;
    private int curSongIndex;
    private String playlistName;
    private String imgPath;
    private final int playlistId;

    public Playlist(String playlistName, String imgPath, int playlistId) {
        this.playlistName = playlistName;
        this.imgPath = imgPath;
        this.playlistId = playlistId;
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
    public Song[] getSongArr(){
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
    public void startPlaylist(int songId){

    }
    public void startPlaylist(){

    }
}
