/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.core;

import java.io.IOException;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

/**
 *
 * @author pguan
 */
public class ASimplePractice {
    private MyMatchLoader loader;
    
    public ASimplePractice(MyMatchLoader loader) {
        this.loader = loader;
    }
    
    public void evaluate() throws IOException, Exception {
        Instances data= loader.getInstances();
        SimpleKMeans cluster = new SimpleKMeans();
        cluster.setNumClusters(4);
        cluster.buildClusterer(data);
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(cluster);
        eval.evaluateClusterer(data);
        System.out.println(eval.clusterResultsToString());        
    }
    
    public static void main(String[] args) throws Exception {
        MyMatchLoader loader = new MyMatchLoader();
        ASimplePractice ap = new ASimplePractice(loader);
        ap.evaluate();
    }
}
