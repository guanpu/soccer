/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.core.classifiers.bayesian;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import soccer.entity.Matches;
import soccer.entity.Model1;

/**
 *
 * @author pguan
 */
public class MyBayesEstimator {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("soccerPU");
    private EntityManager em;
    private List<Matches> matches;
    private MyBayes bn;

    public MyBayesEstimator() throws Exception {
        this.em = emf.createEntityManager();

        Query matchQuery = em.createQuery("SELECT t FROM Matches t where (t.b365a>0) AND (t.leagueId=1729 OR t.leagueId=21518 OR t.leagueId=10257)");
        this.matches = matchQuery.getResultList();
        
        this.bn = new MyBayes();
    }
    
    /**
     * Print the total win or lose
     * @throws Exception 
     */
    public void estimateMoney() throws Exception {
        float total = 0;
        int k = 0;
        int z = matches.size();
        for (int i = 0; i < z; i++) {
            Matches m = matches.get(i);
            double[] instance = getMyJudge(m);
            if (null == instance) {
                continue;
            } else {
                total = total - 1.0f;
                k++;
                double[] odds = getBetOdds(m);
                if (m.getHomeTeamGoal() > m.getAwayTeamGoal()) {
                    System.out.printf("%f, %f, %f, %f, %f, %f, %d \n", instance[0], instance[1], instance[2], odds[0], odds[1], odds[2], 0);
                    if(instance[0]>instance[1] && instance[0]>instance[2]) {                        
                        total = total + m.getB365h();
                    }
                } else if (m.getHomeTeamGoal() < m.getAwayTeamGoal()) {
                    System.out.printf("%f, %f, %f, %f, %f, %f, %d \n", instance[0], instance[1], instance[2], odds[0], odds[1], odds[2], 2);
                    if (instance[2] > instance[1] && instance[2] > instance[0]) {
                        total = total + m.getB365a();
                    }
                } else {
                    System.out.printf("%f, %f, %f, %f, %f, %f, %d \n", instance[0], instance[1], instance[2], odds[0], odds[1], odds[2], 1);
                    if (instance[1] > instance[0] && instance[1] > instance[2]) {
                        total = total + m.getB365d();
                    }
                }
            }            
        }
        
        System.out.printf("final cash being %f with %d bets \n", total, k);

    }
    
    public void estimateRatio() throws Exception {
        int total = 0;
        int k = 0;
        int z = matches.size();
        for (int i = 0; i < z; i++) {
            Matches m = matches.get(i);
            double[] instance = getMyJudge(m);
            if (null == instance) {
                continue;
            } else {
                total++;
                double[] odds = getBetOdds(m);
                if (m.getHomeTeamGoal() > m.getAwayTeamGoal()) {
                    if (instance[0] > odds[0]) {
                        k++;
                    }
                } else if (m.getHomeTeamGoal() < m.getAwayTeamGoal()) {
                    if (instance[2] > odds[2]) {
                        k++;
                    }
                } else {
                    if (instance[1] > odds[1]) {
                        k++;
                    }
                }
            }
        }

        System.out.printf("The ratio that mine matches more than bookeeper is %f, with %d, %d \n", k/(total*1.0), k, total);

    }
    
    /**
     * Get the bet odd.
     * @param m
     * @return 
     */
    private double [] getBetOdds(Matches m) {
        double win = m.getB365h();
        double draw = m.getB365d();
        double lose = m.getB365a();
        
        double level = 1.0d/win + 1.0d/draw + 1.0f/lose;
        double[] toReturn = new double[3];
        toReturn[0] = (1.0d / win) / level;
        toReturn[1] = (1.0d / draw) / level;
        toReturn[2] = (1.0d / lose) / level;
        return toReturn;
    }
    
    /**
     * Create a valid instance which is compliant with MyBayes model so that it can be estimated.
     * @param m
     * @return
     * @throws Exception 
     */
    private double [] getMyJudge(Matches m) throws Exception {
        //to get teamdiff,profile,rank
        Query query = em.createQuery("SELECT t FROM Model1 t where t.homeExpRank>0 and t.awayExpRank>0 and t.awayMorale is not null and t.homeMorale is not null and t.id = :Id");
        query.setParameter("Id", m.getMatchApiId());
        List<Model1> list = query.getResultList();
        if(null==list || list.isEmpty()) {
            return null;
        }
        Model1 model = list.get(0);
        
        double[] toReturn = new double[4];
        int power = 0;
        power += model.getP1();
        power += model.getP2();
        power += model.getP3();
        power += model.getP4();
        power += model.getP5();
        power += model.getP6();
        power += model.getP7();
        power += model.getP8();
        power += model.getP9();
        power += model.getP10();
        power += model.getP11();
        
        power -= model.getP12();
        power -= model.getP13();
        power -= model.getP14();
        power -= model.getP15();
        power -= model.getP16();
        power -= model.getP17();
        power -= model.getP18();
        power -= model.getP19();
        power -= model.getP20();
        power -= model.getP21();
        power -= model.getP22();
        
        toReturn[0] = power/11.0;
        toReturn[1] = (model.getHomeMorale() - model.getAwayMorale())*1.0;
        toReturn[2] = (model.getHomeExpRank() - model.getAwayExpRank())*1.0;
        toReturn[3] = 1.0d;
        return bn.classify(toReturn);       
    }
    
    
    public static void main(String[] args) throws Exception {
        MyBayesEstimator mbe = new MyBayesEstimator();
        mbe.estimateMoney();
    }
    
    
}
