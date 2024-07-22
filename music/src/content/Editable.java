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
     * Add icon to file system then make icon the result of new Path
     *
     * @param newIcon path to new icon
     * @return The new path to the content icon image.
     */
    Path setIcon(Path newIcon);
    Path setIcon(int imgId);

    /**
     * Deletes the editable object.
     *
     * <p> This method deletes the object, from the folder or playlist it is in.
     */
    void delete();
}
