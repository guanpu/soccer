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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import soccer.mis.Utils;

/**
 * The main project.
 * @author pguan
 */
public class Soccer {
    public static final String SQLITE_URL="jdbc:sqlite:/installed/originaldata/database.sqlite";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("soccerPU");
    private static Connection conn;
    private static EntityManager em;
    private static Logger logger = Logger.getLogger(Soccer.class.getName());
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, Exception {
        try {
            conn = DriverManager.getConnection(SQLITE_URL);
            em = emf.createEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            transferData();
            et.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
    }
    public static void transferData() throws ClassNotFoundException, Exception {
        insertIndependent("select * from country", "soccer.entity.Country");
        insertIndependent("select * from match", "soccer.entity.Matches");
        insertIndependent("select * from league", "soccer.entity.League");
        insertIndependent("select * from player", "soccer.entity.Player");
        insertIndependent("select * from team", "soccer.entity.Team");
        insertIndependent("select * from player_attributes", "soccer.entity.PlayerAttributes");
        insertIndependent("select * from team_attributes", "soccer.entity.TeamAttributes");
    }
    
    public static void insertIndependent(String query, String classString) throws SQLException, ClassNotFoundException, InstantiationException, Exception {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        Field[] fields = Class.forName(classString).getDeclaredFields();
        while (resultSet.next()) {
            Object T = Class.forName(classString).newInstance();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String labelName = Utils.getLabelName(field);
                String setterString = Utils.getSetterName(field);
                Class<?> typeClass = field.getType();
                if (labelName == null) {
                    logger.log(Level.INFO, "No Such Column found");
                    continue;
                }
                Method method = Class.forName(classString).getMethod(setterString, typeClass);
                switch (typeClass.getSimpleName()) {
                    case "Long":
                        method.invoke(T, resultSet.getLong(labelName));
                        break;
                    case "Integer":
                        method.invoke(T, resultSet.getInt(labelName));
                        break;
                    case "String":
                        method.invoke(T, resultSet.getString(labelName));
                        break;
                    case "Float":
                        method.invoke(T, resultSet.getFloat(labelName));
                        break;
                    default:
                        method.invoke(T, typeClass.cast(resultSet.getObject(labelName)));
                }
            }
            em.persist(T);
        }
    }
    
}
