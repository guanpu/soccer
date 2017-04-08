/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import soccer.entity.Matches;

/**
 * DTO object that wraps values of a match to form a classifier compliant "instance".
 * @author pguan
 */
public class MatchDto {
//    @PersistenceUnit(unitName = "soccerPU") no JEE container, thus can't use this
    private EntityManagerFactory emf=Persistence.createEntityManagerFactory("soccerPU");
    private final EntityManager em;
    private final String query;
    public MatchDto(String query) {
        this.em = emf.createEntityManager();
        this.query = query;
    }
    
    /**
     * Return a training instance populated with all bet odds.
     * @return 
     */
    public double[] getBetInstance() {
        return null;
        
    }
    
    /**
     * Return a training instance populated with normalized(0-100) doubles which represent a match.
     * @return 
     */
    public double[] getPerSeInstance() {
        return null;
    }
    
    private List<Matches> getMatches() {
        Query query =  em.createQuery(this.query);
        List<Matches> matches = query.getResultList();
        return matches;
    }
    
    private double[] formatMatchIntoFactors() {
        /**
         * Three kind of factors: 
         * 1. absolute power = mean players rating & arxiv & max(equal five highest)
         * 2. team tactic/style = team attributes
         * 3. will_power/state = away/home + stage + recent-results + mutual-history
         * To Be Added:
         * conjunction_1_2
         * conjunction_1_3
         * conjunction_2_3
         */
        double[] factors = new double[10];
        
        
        return factors;
    }
    
    private double[] formatBetIntoFactors() {
        return null;        
    }
    
    /**
     * The test method of this DTO.
     * @param args 
     */
    public static void main(String[] args) {
        /**
         * Use this code snippet to manually generate sql.
         */
        Map properties = new HashMap();
        properties.put("javax.persistence.schema-generation.scripts.action", "create");
        properties.put("javax.persistence.schema-generation.scripts.create-target", "create-schema.sql");

        Persistence.generateSchema("soccerPU", properties);
//        MatchDto dto  = new MatchDto("SELECT t FROM Matches t");
//        List<Matches> list = dto.getMatches();
//        System.out.printf("%d \n", list.size());
        
    }
    
}
