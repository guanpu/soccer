/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main project.
 * @author pguan
 */
public class Soccer {
    public static final String SQLITE_URL="jdbc:sqlite:/installed/originaldata/database.sqlite";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        connect();
    }
    public static void connect() {
        try {
            Connection conn = DriverManager.getConnection(SQLITE_URL);
            try {
                Statement statement = conn.createStatement();
                Map<String, Class<?>> map = conn.getTypeMap();
                System.out.println(map.toString());
                ResultSet resultSet = statement.executeQuery("select * from Match");
                
                System.out.printf("there's %d records", resultSet.getInt(1));
            } catch (SQLException ex) {
                Logger.getLogger(Soccer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Soccer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
