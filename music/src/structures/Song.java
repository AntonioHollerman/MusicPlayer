package structures;

import content.Content;
import content.ContentType;
import db.DbManager;
import db.DbService;

import java.nio.file.Path;
import java.util.List;

public final class Song extends Content {
    private final Path songPath;
    private static final DbManager dbConn = DbService.DB_CONNECTION;

    /**
     * The Song class represents a song object that can be played.
     *
     * <p>
     * A song has a path to the audio file along with an optional image path, ID, and title.
     * </p>
     */
    public Song(Path songPath, int iconId, String title, int songId) {
        super(ContentType.SONG, iconId, title, songId);
        this.songPath = songPath;
        StructuresReferences.songs.add(this);
    }

    /**
     * Retrieves the path of the song.
     *
     * @return the path of the song
     */
    public Path getSongPath() {
        return songPath;
    }

    /**
     * Deletes the editable object.
     *
     * <p> This method deletes the object, from the folder or playlist it is in.
     */
    @Override
    public void delete() {
        List<Integer> playlistReferences = dbConn.getSongPlaylistReferencesIds(getId());
        for (Playlist playlist : StructuresReferences.playlists){
            if (playlistReferences.contains(playlist.getId())){
                playlist.removeSong(getId());
            }
        }
        dbConn.deleteSong(getId());
        StructuresReferences.songs.remove(this);
    }
}
