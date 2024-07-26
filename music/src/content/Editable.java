package content;

import java.nio.file.Path;

/**
 * The Editable interface represents an object that can be edited, which includes
 * setting the title, setting the icon, and deleting the object from its container.
 * <p>
 * Main purpose is to be used in EditPanel in gui package to use the panel to edit Folders, Playlists, and Songs
 */
public sealed interface Editable permits Content {
    /**
     * Sets the title for the content.
     *
     * @param newTitle the title the song changed to
     */
    void setTitle(String newTitle);

    /**
     * Creates new icon image and sets icon id to it
     * @param path path to new image to add
     */
    void setIcon(Path path);

    /**
     * Updates content image path
     * @param iconId The id of the image to set new icon to
     */
    void setIcon(int iconId);

    /**
     * Deletes the editable object.
     *
     * <p> This method deletes the object, from the folder or playlist it is in.
     */
    void delete();
}
