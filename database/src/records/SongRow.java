package records;

import java.nio.file.Path;

public record SongRow(int id, int imgId, String title, Path songPath) {
}
