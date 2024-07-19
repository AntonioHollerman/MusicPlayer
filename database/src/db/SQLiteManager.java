package db;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteManager extends DbManager{
    protected SQLiteManager() throws SQLException {
        super(DriverManager.getConnection("jdbc:sqlite:C:\\JavaProjects\\MusicPlayer\\Music.db"));

        Path abDbPath = Path.of("Music.db").toAbsolutePath();
        boolean dbExists = Files.exists(abDbPath);

        if (!dbExists){
                createTables();
        }
    }
}
