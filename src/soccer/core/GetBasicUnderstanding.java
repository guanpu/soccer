/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.core;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.commons.math3.stat.StatUtils;
import soccer.entity.Matches;
import soccer.entity.Model1;

/**
 *
 * @author pguan
 */
public class GetBasicUnderstanding {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("soccerPU");
    private EntityManager em;
    private List<Model1> list;
    private List<Matches> matchlist;
    private static final double THRESHOLD = 3.75;

    public GetBasicUnderstanding() {
        this.em = emf.createEntityManager();

        Query attrQuery = em.createQuery("SELECT t FROM Model1 t where t.homeExpRank>0");
        this.list = attrQuery.getResultList();
        Query matchQuery = em.createQuery("SELECT t FROM Matches t where t.matchDate>'2013-07-01 00:00:00'");
        this.matchlist = matchQuery.getResultList();
    }
    
    
    public void printBasicResults() {
        long total = matchlist.stream().count();
        
        long uw = matchlist.stream().filter(LambdaGenerator.getUnExpectedWin()).count();
        long totalExpWin = matchlist.stream().filter((Matches m)->{return m.getLba()> THRESHOLD || m.getLbh()>THRESHOLD;}).count();
        long ud = matchlist.stream().filter(LambdaGenerator.getUnExpectedDraw()).count();
        long totalExpDraw = matchlist.stream().filter((Matches m)->{return m.getLbd()> THRESHOLD;}).count();
        
        System.out.println("Threshold is " + THRESHOLD);
        System.out.println("Total expected win is " + totalExpWin);
        System.out.printf("The ratio of unexpected win is %f\n", uw/(totalExpWin*1.0));
        
        System.out.println("Total expected non-draw is " + totalExpDraw);
        System.out.printf("The ratio of unexpected draw is %f\n", ud/(totalExpDraw*1.0));
        
        System.out.printf("The ratio of unexpected is %f\n", (uw+ud)/(total*1.0));
    }
    
    public static void main(String[] args) {
        GetBasicUnderstanding gb = new GetBasicUnderstanding();
        gb.printBasicResults();
    }
    
    static class LambdaGenerator{
        
        public static Predicate<Matches> getUnExpectedWin() {
            return (Matches m) -> {
                if(m.getLbh() > THRESHOLD && m.getHomeTeamGoal()>m.getAwayTeamGoal()) {
                    return true;
                }
                if (m.getLba() > THRESHOLD && m.getHomeTeamGoal() < m.getAwayTeamGoal()) {
                    return true;
                }
                return false;
            };
        }
        
        public static Predicate<Matches> getUnExpectedDraw() {
            return (Matches m) -> {
                if (m.getLbd() > THRESHOLD && m.getHomeTeamGoal() == m.getAwayTeamGoal()) {
                    return true;
                }
                return false;
            };
        }
    
    }
    
}
