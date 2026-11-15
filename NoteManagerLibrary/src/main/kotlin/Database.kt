package notemanager.library

import java.sql.DriverManager
import java.sql.PreparedStatement

class Database {
    //what is DriveManager: a JDBC class that manages database connections, getConnection returns a connected object through the connection string.



    //establish connection with Database named notes.db, if it does not exist it creates it
    // returns a connection object
    private val connection = DriverManager.getConnection("JDBC:sqlite:notes.db")

    //equal sign used for one line function statement
    //wrapper function for Preparestatment in the connection object
    fun prepareStatement(sql: String): PreparedStatement = connection.prepareStatement(sql)

        //overloaded function for aiding in retreving noteID
    fun prepareStatement(
        sql: String,
        generatedKeys: Int
    ): PreparedStatement =
        connection.prepareStatement(sql, generatedKeys)


    //Function to create necessary tables
    // update/delete on cascade - if an entry is deleted in parent table it is also deleted in child tables.
    fun initializeDatabase() {
        // INTEGER PRIMARY KEY AUTOINCREMENT automatically creates an integer primary key
        //DELETE CASCADE will delete note from associated tables.

        var sql = """
            CREATE TABLE IF NOT EXISTS Notes (
           noteID INTEGER PRIMARY KEY AUTOINCREMENT, 
           title TEXT,
           noteBody TEXT,
           timeCreated TEXT,
           noteType TEXT
            );
        """

        connection.prepareStatement(sql).use {
            it.executeUpdate()
        }
        sql = """
                  CREATE TABLE IF NOT EXISTS MentalNotes(
                  noteID INTEGER PRIMARY KEY,
                  emotion TEXT,
                  FOREIGN KEY (noteID)
                      REFERENCES Notes(noteID)
                      ON UPDATE CASCADE ON DELETE CASCADE
            );
            """

        connection.prepareStatement(sql).use {
            it.executeUpdate()
        }

        sql = """
                CREATE TABLE IF NOT EXISTS ReminderNotes(
              noteID INTEGER PRIMARY KEY,
              deadLine TEXT,
              FOREIGN KEY (noteID)
                  REFERENCES Notes(noteID)
                  ON UPDATE CASCADE ON DELETE CASCADE
                  );
            """
        connection.prepareStatement(sql).use {
            it.executeUpdate()
        }
            sql = """
                CREATE TABLE IF NOT EXISTS GeneralNotes(
              noteID INTEGER PRIMARY KEY,
              FOREIGN KEY (noteID)
                  REFERENCES Notes(noteID)
                  ON UPDATE CASCADE ON DELETE CASCADE
            ); 
            """

            connection.prepareStatement(sql).use {
                it.executeUpdate()
            }




    }
    init {
        connection.createStatement().use {
            it.execute("PRAGMA foreign_keys = ON") //enforce foreign key constraints, basically without this update on cascade does not work as expected.
        }                                                 // you will get an empty row with an id  no entry despite it having been deleted.
        initializeDatabase()
    }


}