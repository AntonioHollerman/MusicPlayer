package content;

import db.DbManager;
import db.DbService;

import java.nio.file.Path;

public abstract non-sealed class Content implements Editable {
    private final ContentType type;
    private final int id;
    private int iconId;
    private String title;
    private static final DbManager dbConn = DbService.DB_CONNECTION;

    /**
     * Represent the type of content that can be displayed in a folder or playlist.
     * @param type States what type the current content is
     * @param iconId the id of the path to the content image
     * @param title Title of the content
     */
    public Content(ContentType type, int iconId, String title, int id) {
        this.type = type;
        this.iconId = iconId;
        this.title = title;
        this.id = id;
    }

    public final int getId() {
        return id;
    }

    /**
     * Returns the type of content the object is
     * @return The type of content
     */
    public final ContentType getType(){
        return type;
    }

    /**
     * get the current icon path
     * @return the String of the path to the icon image
     */
    public final Path getIconPath(){
        return dbConn.getImgPath(iconId);
    }

    /**
     * Gets the current title for the content
     * @return String of the title for the object
     */
    public final String getTitle(){
        return title;
    }

    /**
     * Creates new icon image and sets icon id to it
     * @param path path to new image to add
     */
    public final void setIcon(Path path){
        setIcon(dbConn.insertNewImage(path));
    }

    /**
     * Updates content image path
     * @param iconId The id of the image to set new icon to
     */
    public final void setIcon(int iconId){
        this.iconId = iconId;
        switch (type){
            case SONG -> dbConn.setSongImg(id, iconId);
            case PLAYLIST -> dbConn.setPlaylistImg(id, iconId);
            case FOLDER -> dbConn.setFolderImg(id, iconId);
        }
    }

    public final void setTitle(String newTitle){
        this.title = newTitle;
        switch (type){
            case SONG -> dbConn.setSongName(id, newTitle);
            case PLAYLIST -> dbConn.setPlaylistName(id, newTitle);
            case FOLDER -> dbConn.setFolderName(id, newTitle);
        }
    }
}
