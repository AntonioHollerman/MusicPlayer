package music;

public interface Editable {
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
