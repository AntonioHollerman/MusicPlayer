package music;

public final class Song implements Editable{
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
    public void play(){

    }
    public void pause(){

    }

    /**
     * Sets the title of the song.
     *
     * @param title the new title of the song
     */

    public void setTitle(String title) {
        this.title = title;
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
