/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.core.classifiers.bayesian;

import java.io.IOException;
import org.apache.commons.math3.stat.StatUtils;
import soccer.mis.ModelForBN;
import weka.classifiers.bayes.BayesNet;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;

/**
 *
 * @author pguan
 */
public class MyBayes {
    private ModelForBN loader;
    private BayesNet bn;
    private Filter filter2;
    private Filter filter3;
    private Instances localInstances;
    private static final String NETWORK_STRUCTURE_PATH = "F:\\codes\\workspace\\basian-network\\models\\bif.xml";
    public MyBayes(ModelForBN loader, BayesNet bn) {
        this.loader = loader;
        this.bn = bn;
    }

    public MyBayes() throws Exception {
        this.loader = new ModelForBN();
        this.setup();
        this.evaluate();
    }
    
    public void setup() throws Exception {
        bn = new BayesNet();
        bn.setOptions(new String[]{
            "-Q", "weka.classifiers.bayes.net.search.fixed.FromFile", "--", "-B", NETWORK_STRUCTURE_PATH,
            "-E", "weka.classifiers.bayes.net.estimate.SimpleEstimator","--", "-A", "0.6"
        });
    }

    public void evaluate() throws IOException, Exception {
        Instances data = loader.buildInstances();
        
        ReplaceMissingValues replaceMissingValues = new ReplaceMissingValues();
        replaceMissingValues.setInputFormat(data);
        data = Filter.useFilter(data, replaceMissingValues);
        
        
        Discretize toNominal = new Discretize();
        toNominal.setOptions(new String[]{
            "-R", "1,3",
            "-B", "8"
        });
        toNominal.setInputFormat(data);
        this.filter2=toNominal;
        data = Filter.useFilter(data, toNominal);
        
        Discretize discretize = new Discretize();
        discretize.setOptions(new String[]{
            "-R", "2",
            "-B", "3"
        });
        discretize.setInputFormat(data);
        this.filter3=discretize;
        data = Filter.useFilter(data, discretize);
        
        localInstances = data;
        
        bn.buildClassifier(data);
//        System.out.println(bn.toXMLBIF03());
    }
    
    public double [] classify(double [] values) throws Exception {
        Instance inst= new DenseInstance(1.0, values);
        inst.setDataset(new ModelForBN().getInstances());
        inst.setClassMissing();
        
        filter2.input(inst);
        filter2.batchFinished();
        inst = filter2.output();
        
        filter3.input(inst);
        filter3.batchFinished();
        inst = filter3.output();
        
        return bn.distributionForInstance(inst);
                
    }
    public void totalTest() throws Exception {
        int j = 0;
        int z = localInstances.size();
        for (int i = 0; i < z; i++) {
            Instance instance = localInstances.get(i);
            double[] result = bn.distributionForInstance(instance);
            double classval = instance.classValue();
            if (getAbCorr(result, classval)) {
                j++;
            } else {
                System.out.printf("%f, %f, %f, %f \n", result[0], result[1], result[2], classval);
            }
        }
        double ratio = j / (z * 1.0);
        System.out.println(ratio);
    }

    public boolean getAbCorr(double[] result, Double classval) {
        double myPos = StatUtils.max(result);
        return result[classval.intValue()] == myPos;
    }

    public static void main(String[] args) throws Exception {
        MyBayes mb = new MyBayes();
        mb.totalTest();
    }
}
