package music;

/**
 * The Editable interface represents an object that can be edited, which includes
 * setting the title, setting the icon, and deleting the object from its container.
 * <p>
 * Main purpose is to be used in EditPanel in gui package to use the panel to edit Folders, Playlists, and Songs
 */
public sealed interface Editable permits Song, Playlist, Folder {
    /**
     * Sets the title of the editable object*/
    void setTitle(String Text);

    /**
     * Sets the icon of the editable object.
     *
     * @param path the path to the icon file
     */
    void setIcon(String path);

    /**
     * Deletes the editable object.
     *
     * <p> This method deletes the object, from the folder or playlist it is in.
     */
    void delete();
}
