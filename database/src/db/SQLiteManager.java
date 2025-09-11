package db;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteManager extends DbManager{
    private static final Path dbPath = Path.of("data/Music.db").toAbsolutePath();

    protected SQLiteManager() throws SQLException {
        super(DriverManager.getConnection("jdbc:sqlite:" + Path.of("data/Music.db").toAbsolutePath()));

        boolean dbExists = Files.exists(dbPath);

        if (dbExists){
            createTables();
        } else {
            throw new SQLException("Database does not exist");
        }
    }
}
