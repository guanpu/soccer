/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.core.models;

import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import soccer.entity.Matches;
import soccer.entity.Model1;
import soccer.entity.PlayerAttributes;

/**
 * Class to create data into database model1 table.
 * @author pguan
 */
public class BuildModel1 {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("soccerPU");
    private EntityManager em;
    private List<Matches> matches;
    private final List<PlayerAttributes> attrs;


    public BuildModel1() {
        this.em = emf.createEntityManager();

        Query matchQuery = em.createQuery("SELECT t FROM Matches t");
        this.matches = matchQuery.getResultList();

        Query attrQuery = em.createQuery("SELECT t FROM PlayerAttributes t ORDER BY t.playerDate");
        this.attrs = attrQuery.getResultList();
    }

    public void persistModel() throws Exception {
        EntityTransaction et = em.getTransaction();
        et.begin();
        matches.forEach((m) -> {
            try {
                Model1 model1 = new Model1();
                model1.setId(m.getMatchApiId().longValue());
                setPlayerAttrForModel(m, model1);
                int homeGoal = m.getHomeTeamGoal();
                model1.setHg(homeGoal);
                int awayGoal = m.getAwayTeamGoal();
                model1.setAg(awayGoal);
                if (homeGoal - awayGoal > 0) {
                    model1.setFinalResult(1);
                } else if (homeGoal - awayGoal < 0) {
                    model1.setFinalResult(-1);
                } else {
                    model1.setFinalResult(0);
                }
                em.persist(model1);
            } catch (Exception ex) {
                Logger.getLogger(PlayerModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        et.commit();
    }
    
    public void setPlayerAttrForModel(Matches m, Model1 model) throws Exception {
        final String dt = m.getMatchDate();
        for (int i = 1; i < 12; i++) {
            Method md = Matches.class.getMethod("getHomePlayer" + i);
            Method setterMethod = Model1.class.getMethod("setP" + i, Integer.class);
            final Long id = (Long) md.invoke(m);
            PlayerAttributes[] attr = attrs.stream().filter((PlayerAttributes p) -> {
                return p.getPlayerApiId().equals(id);
            }).toArray(PlayerAttributes[]::new);
            Long rateing = 0L;
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
            setterMethod.invoke(model, rateing.intValue());
        }
        for (int i = 1; i < 12; i++) {
            Method md = Matches.class.getMethod("getAwayPlayer" + i);
            int index = i+11;
            Method setterMethod = Model1.class.getMethod("setP" + index, Integer.class);
            final Long id = (Long) md.invoke(m);
            PlayerAttributes[] attr = attrs.stream().filter((PlayerAttributes p) -> {
                return p.getPlayerApiId().equals(id);
            }).toArray(PlayerAttributes[]::new);
            Long rateing = 0L;
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
            setterMethod.invoke(model, rateing.intValue());
        }
    }
    
    public static void main(String[] args) throws Exception {
        BuildModel1 bm = new BuildModel1();
        bm.persistModel();
    }
    
}
