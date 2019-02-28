//Code written by Hjalmar
//      for the ServerStats class in this same project helped.
//Also,
// Sqlite Java code found at http://www.sqlitetutorial.net/sqlite-java/select/
//
// helped.

package se.iths.mhb.plugin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    private Connection connect() {
        final String path = "jdbc:sqlite:DB/SimplePhrases/mhbphrases.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(path);
        } catch (SQLException sqle) {
            System.err.println("connection err: " + sqle);
        }
        return connection;
    }

    public List<PhraseObject> retrievePhrases() {
        List<PhraseObject> returnedPhrases = new ArrayList<>();
        try {
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM phrases;");
            while (rs.next()) {
                returnedPhrases.add( new PhraseObject(
                        rs.getInt("id"),
                        rs.getString("phrase"),
                        rs.getString("reference")
                ) );

            }
        } catch (SQLException sqle) {
            System.err.println("SimplePhrases SQL err:" + sqle);
        }
        return returnedPhrases;
    }

}
