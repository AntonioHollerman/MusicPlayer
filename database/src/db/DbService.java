package db;

import java.sql.SQLException;

// Hosts public database
public class DbService {
    public static final DbManager DB_CONNECTION;

    static {
        try {
            DB_CONNECTION = new SQLiteManager();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
