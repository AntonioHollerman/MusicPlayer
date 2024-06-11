package main;

import database.DbManager;
import database.MySQLManager;

/**
 * The Main class serves as the entry point for the application.
 * It contains the main method that is executed when the program starts.
 */
public class Main {
    public static final DbManager DB_CONNECTION = new MySQLManager();
    public static void main(String[] args) {

    }
}