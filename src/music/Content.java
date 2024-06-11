package music;

public abstract non-sealed class Content implements Editable {
    private final ContentType type;
    private final int id;
    protected String iconPath;
    protected String title;

    /**
     * Represent the type of content that can be displayed in a folder or playlist.
     * @param type States what type the current content is
     * @param iconPath the path to the content image
     * @param title Title of the content
     */
    public Content(ContentType type, String iconPath, String title, int id) {
        this.type = type;
        this.iconPath = iconPath;
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
    public final String getIconPath(){
        return iconPath;
    }

    /**
     * Gets the current title for the content
     * @return String of the title for the object
     */
    public final String getTitle(){
        return title;
    }

    /**
     * Sets the path to the content icon image.
     *
     * @return The new path to the content icon image.
     */
    public abstract String setIconPath();

    /**
     * Sets the title for the content.
     */
    public abstract void setTitle();
}
