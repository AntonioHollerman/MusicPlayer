module database {
    requires java.sql;
    requires org.slf4j;

    exports db;
    exports records;
}