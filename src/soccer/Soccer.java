/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import soccer.entity.Matches;
import soccer.mis.Utils;

/**
 * The main project.
 * @author pguan
 */
public class Soccer {
    public static final String SQLITE_URL="jdbc:sqlite:/installed/originaldata/database.sqlite";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, Exception {
        connect();
    }
    public static void connect() throws ClassNotFoundException, Exception {
        try {
            Connection conn = DriverManager.getConnection(SQLITE_URL);
            try {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from Match");
                Field[] fields = Class.forName("soccer.entity.Matches").getDeclaredFields();
//                while (resultSet.next()) {
                    Object T = Class.forName("soccer.entity.Matches").newInstance();
                    for (int i = 0; i < fields.length; i++) {
                        Field field = fields[i];
                        String labelName = Utils.getLabelName(field);
                        System.out.println(labelName);
                        String setterString = Utils.getSetterName(field);
                        Class<?> typeClass = field.getType();
                        if(labelName==null) {
                            continue;
                        }
                        Method method = Class.forName("soccer.entity.Matches").getMethod(setterString, typeClass);
                        switch(typeClass.getSimpleName()) {
                            case "Long":
                                method.invoke(T, resultSet.getLong(labelName));
                                break;
                            case "Integer":
                                method.invoke(T, resultSet.getInt(labelName));
                                break;
                            case "String":
                                method.invoke(T, resultSet.getString(labelName));
                                break;
                            default:
                                method.invoke(T, typeClass.cast(resultSet.getObject(labelName)));
                        }
                    }
                    Matches m = (Matches) T;
                    System.out.println(m);
                    //TODO: Done here, the match is successfully set, expect that the database have changed to be 'final_football' to eliminate the 'cross' field problem.
//                }
            } catch (SQLException ex) {
                Logger.getLogger(Soccer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Soccer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
