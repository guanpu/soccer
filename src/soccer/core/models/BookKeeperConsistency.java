/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.core.models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.math3.stat.StatUtils;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 * This model used to clustering bookkeeper's consistency to check how diverse
 * they can be for a match.
 *
 * @author pguan
 */
public class BookKeeperConsistency {

    private final CSVLoader loader;
    private Instances is;

    public BookKeeperConsistency() throws Exception {
        this.loader = new CSVLoader();
        loader.setOptions(new String[]{
            "-H",
            "-R", "first-last"
        });
        loader.setSource(new File("F:\\codes\\workspace\\soccer\\tmp\\keepers.csv"));
        ArrayList<Attribute> attrs = new ArrayList<>();
        Attribute dv = new Attribute("drawVar");
        Attribute hv = new Attribute("homeVar");
        Attribute av = new Attribute("awayVar");
        Attribute dm = new Attribute("drawMean");
        Attribute hm = new Attribute("homeMean");
        Attribute am = new Attribute("awayMean");
        attrs.add(av);
        attrs.add(dv);
        attrs.add(hv);
        attrs.add(am);
        attrs.add(dm);
        attrs.add(hm);

        this.is = new Instances("variances", attrs, 0);
    }

    public Instances getInstances() throws Exception {
        Instances instances = loader.getDataSet();

        setInstances(instances);

        return Instances.mergeInstances(is, instances);
    }
    
    public void showTheInstance(int index) throws IOException {
        Instances instances = loader.getDataSet();
        Instance i = instances.get(index);
        System.out.println(i.toString());        
    }

    public void setInstances(Instances instances) {
        double[][] result = new double[2756][6];
        for (int j = 0; j < 3; j++) {
            double[][] m = new double[10][2756];
            double[][] transformed = new double[2756][10];
//            double[] mean = new double[2576];
//            double[] var = new double[2576];
            for (int i = 0; i < 10; i++) {
                m[i] = instances.attributeToDoubleArray(j + 3 * i);
            }
            transformed = transposeMatrix(m);
            for (int i = 0; i < 2756; i++) {
//                mean[i] = StatUtils.mean(transformed[i]);
//                var[i] = StatUtils.variance(transformed[i], mean[i]);
                double mean = StatUtils.mean(transformed[i]);
                result[i][j+3] = mean;
                result[i][j] = StatUtils.variance(transformed[i])/mean;
            }
        }
        for (int i = 0; i < 2756; i++) {
            result[i][0] = result[i][0]+result[i][1]+result[i][2];
            Instance inst = new DenseInstance(1.0, result[i]);
            is.add(inst);            
        }
    }

    public static double[][] transposeMatrix(double[][] m) {
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                temp[j][i] = m[i][j];
            }
        }
        return temp;
    }

}
