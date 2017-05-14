/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.core.models;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import soccer.entity.Matches;
import soccer.entity.PlayerAttributes;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.DenseInstance;
/**
 *
 * @author pguan
 */
public class PlayerModel {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("soccerPU");
    private EntityManager em;
    private Instances instances;
//    private List<Player> players;
    private final List<PlayerAttributes> attrs;
    private List<Matches> matches;

    public PlayerModel() {
        this.em = emf.createEntityManager();
        ArrayList<Attribute> attrs = new ArrayList<>();
        Attribute homescore = new Attribute("homescore");
        Attribute awayscore = new Attribute("awayscore");
        ArrayList<String> results = new ArrayList<>();
        results.add("Win");
        results.add("Draw");
        results.add("Lose");
        Attribute result = new Attribute("result", results);
        attrs.add(homescore);
        attrs.add(awayscore);
        attrs.add(result);
        this.instances = new Instances("myinstance", attrs, 0);
        
        Query matchQuery = em.createQuery("SELECT t FROM Matches t");
        this.matches = matchQuery.getResultList();
//
//        Query playerQuery = em.createQuery("SELECT t FROM Player t");
//        this.players = playerQuery.getResultList();

        Query attrQuery = em.createQuery("SELECT t FROM PlayerAttributes t ORDER BY t.playerDate");
        this.attrs = attrQuery.getResultList();
    }

    /**
     * Return the instances of matches, each instance has 3 attribute home player's rating, away player's rating and
     * the final result.
     * @return
     * @throws Exception 
     */
    public Instances buildInstance() throws Exception{
        matches.forEach((m)->{
            try {
                double[] values = getPlayersForMatches(m);
                int homeGoal = m.getHomeTeamGoal();
                int awayGoal = m.getAwayTeamGoal();
                if(homeGoal-awayGoal>0) {
                    values[2] = instances.attribute(2).indexOfValue("Win");
                }else if(homeGoal-awayGoal<0) {
                    values[2] = instances.attribute(2).indexOfValue("Lose");
                }else {
                    values[2] = instances.attribute(2).indexOfValue("Draw");
                }
                Instance inst = new DenseInstance(1.0, values);
                instances.add(inst);
            } catch (Exception ex) {
                Logger.getLogger(PlayerModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return instances;
    }
    
    /**
     * Get the average player rating score for the matches.
     * @param m
     * @return
     * @throws Exception 
     */
    public double[] getPlayersForMatches(Matches m) throws Exception{
        final String dt = m.getMatchDate();
        double[] toreturn = new double[3];
        double home_avg = 0.0;
        double away_avg = 0.0;
        for (int i = 1; i < 12; i++) {
            Method md = Matches.class.getMethod("getHomePlayer"+i);
            final Long id = (Long) md.invoke(m);
            PlayerAttributes[] attr = attrs.stream().filter((PlayerAttributes p) -> {
                return p.getPlayerApiId().equals(id);
            }).toArray(PlayerAttributes[]::new);
            Long rateing = 50L;
            if(attr.length == 0) {
                break;
            }
            
            if(attr.length == 1) {
                rateing = attr[0].getOverallRating();
            }
            
            for (int j = 1; j < attr.length; j++) {
                if(j+1 == attr.length) {
                    rateing = attr[j].getOverallRating();
                    break;
                }
                if(attr[j].getPlayerDate().compareTo(dt)>0) {
                    rateing = attr[j-1].getOverallRating();
                    break;
                }                
            }
            home_avg += rateing * 1.0;          
        }
        toreturn[0] = home_avg/11.0;
        for (int i = 1; i < 12; i++) {
            Method md = Matches.class.getMethod("getAwayPlayer" + i);
            final Long id = (Long) md.invoke(m);
            PlayerAttributes[] attr = attrs.stream().filter((PlayerAttributes p) -> {
                return p.getPlayerApiId().equals(id);
            }).toArray(PlayerAttributes[]::new);
            Long rateing = 50L;
            if (attr.length == 0) {
                break;
            }

            if (attr.length == 1) {
                rateing = attr[0].getOverallRating();
            }

            for (int j = 1; j < attr.length; j++) {
                if (j + 1 == attr.length) {
                    rateing = attr[j].getOverallRating();
                    break;
                }
                if (attr[j].getPlayerDate().compareTo(dt) > 0) {
                    rateing = attr[j - 1].getOverallRating();
                    break;
                }
            }
            away_avg += rateing * 1.0;
        }
        toreturn[1] = away_avg/11.0;
        return toreturn;        
    }
    
    public static void main(String[] args) throws Exception {
        PlayerModel pm = new PlayerModel();
        Instances data = pm.buildInstance();
        SimpleKMeans cluster = new SimpleKMeans();
        cluster.setNumClusters(4);
        cluster.buildClusterer(data);
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(cluster);
        eval.evaluateClusterer(data);
        System.out.println(eval.clusterResultsToString());
    }
    
}
