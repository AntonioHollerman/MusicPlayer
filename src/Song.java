public class Song {
    private String songPath;
    private String imgPath;
    private final int id;
    private String title;
    private boolean isPlaying;

    public Song(String songPath, String imgPath, int id, String title) {
        this.songPath = songPath;
        this.imgPath = imgPath;
        this.id = id;
        this.title = title;
    }

    public String getSongPath() {
        return songPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void play(){

    }
    public void pause(){

    }
}
