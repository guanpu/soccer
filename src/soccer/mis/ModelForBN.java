/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.mis;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.commons.math3.stat.StatUtils;
import soccer.entity.Model1;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author pguan
 */
public class ModelForBN {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("soccerPU");
    private EntityManager em;
    private List<Model1> list;
    private Instances instances;

    public ModelForBN() {
        this.em = emf.createEntityManager();

        Query attrQuery = em.createQuery("SELECT t FROM Model1 t where t.homeExpRank is not null and t.awayExpRank is not null and t.homeMorale is not null and t.awayMorale is not null");

        this.list = attrQuery.getResultList();

        ArrayList<Attribute> attrs = new ArrayList<>();
        Attribute teamdiff = new Attribute("teamdiff");
        Attribute profilediff = new Attribute("profilediff");
        Attribute rankdiff = new Attribute("rankdiff");

        ArrayList<String> results = new ArrayList<>();
        results.add("Win");
        results.add("Draw");
        results.add("Lose");
        Attribute result = new Attribute("result", results);

        attrs.add(teamdiff);
        attrs.add(profilediff);
        attrs.add(rankdiff);
        attrs.add(result);

        this.instances = new Instances("myinstance", attrs, 0);
        instances.setClassIndex(3);
    }

    public Instances buildInstances() {
        list.forEach(m -> {
            try {
                double[] values = new double[4];
                values[0] = getAvgAndVariance(m);
                values[1] = (m.getHomeMorale()-m.getAwayMorale())* 1.0;
                values[2] = (m.getHomeExpRank()-m.getAwayExpRank())* 1.0;
                switch (m.getFinalResult()) {
                    case 1:
                        values[3] = instances.attribute(3).indexOfValue("Win");
                        break;
                    case 0:
                        values[3] = instances.attribute(3).indexOfValue("Draw");
                        break;
                    case -1:
                        values[3] = instances.attribute(3).indexOfValue("Lose");
                        break;
                    default:
                        System.err.println("Error");
                }
                Instance inst = new DenseInstance(1.0, values);
                instances.add(inst);
            } catch (Exception ex) {
                Logger.getLogger(PersistInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return instances;
    }

    public double getAvgAndVariance(Model1 m) throws Exception {
        double[] ints = new double[11];
        for (int i = 1; i < 12; i++) {
            Method getterMethod = Model1.class.getMethod("getP" + i);
            Integer mark = (Integer) getterMethod.invoke(m);
            ints[i - 1] = mark * 1.0;
        }
        double homeavg = StatUtils.mean(ints);
        for (int i = 1; i < 12; i++) {
            int index = i + 11;
            Method getterMethod = Model1.class.getMethod("getP" + index);
            Integer mark = (Integer) getterMethod.invoke(m);
            ints[i - 1] = mark*1.0;
        }
        double awayavg = StatUtils.mean(ints);

        return homeavg - awayavg;
    }

    public Instances getInstances() {
        return instances;
    }
    
    
}
