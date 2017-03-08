/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.dto;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import soccer.entity.Matches;

/**
 *
 * @author pguan
 */
public class MatchDto {
    @PersistenceUnit(unitName = "soccerPU")
    private EntityManagerFactory emf;
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
        
    }
    
    /**
     * Return a training instance populated with normalized(0-100) doubles which represent a match.
     * @return 
     */
    public double[] getPerSeInstance() {
        
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
        
    }
    
    private double[] formatBetIntoFactors() {
        
    }
    
}
