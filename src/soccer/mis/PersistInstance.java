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
import soccer.entity.Model1;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import org.apache.commons.math3.stat.StatUtils;

/**
 *
 * @author pguan
 */
public class PersistInstance {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("soccerPU");
    private EntityManager em;
    private List<Model1> list;
    private Instances instances;


    public PersistInstance() {
        this.em = emf.createEntityManager();

        /**
         * 4008 currently, 8681 with matchdate
         */
        Query attrQuery = em.createQuery("SELECT t FROM Model1 t where t.homeExpRank is not null and t.awayExpRank is not null");

        this.list = attrQuery.getResultList();
        
        ArrayList<Attribute> attrs = new ArrayList<>();
        Attribute homeavg = new Attribute("homeavg");
        Attribute homevari = new Attribute("homevari");
        Attribute awayavg = new Attribute("awayavg");
        Attribute awayvari = new Attribute("awayvari");
        Attribute homeExp = new Attribute("homeExp");
        Attribute awayExp = new Attribute("awayExp");
        Attribute homeId = new Attribute("homeId");
        Attribute awayId = new Attribute("awayId");
        
        ArrayList<String> results = new ArrayList<>();
        results.add("Win");
        results.add("Draw");
        results.add("Lose");
        Attribute result = new Attribute("result", results);
        
        attrs.add(homeavg);
        attrs.add(homevari);
        attrs.add(awayavg);
        attrs.add(awayvari);
        attrs.add(homeExp);
        attrs.add(awayExp);
        //todo: Add recent results, i.e. profile
        attrs.add(result);
        attrs.add(homeId);
        attrs.add(awayId);
        this.instances = new Instances("myinstance", attrs, 0);
    }

    
    public Instances buildInstances() {
        list.forEach(m->{
            try {
                double[] values = getAvgAndVariance(m);
                values[4] = m.getHomeExpRank() * 1.0;
                values[5] = m.getAwayExpRank() * 1.0;
                
                switch (m.getFinalResult()) {
                    case 1:
                        values[6] = instances.attribute(6).indexOfValue("Win");
                        break;
                    case -1:
                        values[6] = instances.attribute(6).indexOfValue("Lose");
                        break;
                    case 0:
                        values[6] = instances.attribute(6).indexOfValue("Draw");
                        break;
                    default:
                        System.err.println("Error");
                }
                values[7] = m.getHomeTeamId().doubleValue();
                values[8] = m.getAwayTeamId().doubleValue();
                Instance inst = new DenseInstance(1.0, values);
                instances.add(inst);
            } catch (Exception ex) {
                Logger.getLogger(PersistInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return instances;
    }
    
    public double[] getAvgAndVariance(Model1 m) throws Exception {
        double[] db = new double[9];
        double[] ints = new double[12];
        for (int i = 1; i < 12; i++) {
            Method getterMethod = Model1.class.getMethod("getP" + i);
            Integer mark = (Integer) getterMethod.invoke(m);
            ints[i-1] = mark*1.0;            
        }
        db[0] = StatUtils.mean(ints);
        db[1] = StatUtils.variance(ints);
        for (int i = 1; i < 12; i++) {
            int index = i + 11;
            Method getterMethod = Model1.class.getMethod("getP" + index);
            Integer mark = (Integer) getterMethod.invoke(m);
            ints[i - 1] = mark;
        }
        db[2] = StatUtils.mean(ints);
        db[3] = StatUtils.variance(ints);
        return db;
    } 
}
