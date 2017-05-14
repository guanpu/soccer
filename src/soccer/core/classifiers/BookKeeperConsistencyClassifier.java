/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.core.classifiers;

import soccer.core.models.BookKeeperConsistency;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.HierarchicalClusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.instance.RemoveWithValues;

/**
 *
 * @author pguan
 */
public class BookKeeperConsistencyClassifier {
    public static void main(String[] args) throws Exception {
        BookKeeperConsistency bkc = new BookKeeperConsistency();
        Instances data = bkc.getInstances();

        RemoveWithValues rwv = new RemoveWithValues();
        rwv.setOptions(new String[]{
            "-C", "4",
            "-S", "6",
            "-V"
        });
        rwv.setInputFormat(data);
        data = Filter.useFilter(data, rwv);
        RemoveWithValues rwv1 = new RemoveWithValues();
        rwv1.setOptions(new String[]{
            "-C", "6",
            "-S", "6",
            "-V"
        });
        rwv1.setInputFormat(data);
        data = Filter.useFilter(data, rwv1);
        
//        Normalize nm = new Normalize();
//        nm.setOptions(new String[]{
//            "-S", "100"
//        });
//        nm.setInputFormat(data);
//        data = Filter.useFilter(data, nm);

        Remove rm = new Remove();
        rm.setOptions(new String[]{
            "-R", "2-last"
        });
        rm.setInputFormat(data);
        Instances newData = Filter.useFilter(data, rm);
        
        SimpleKMeans cluster = new SimpleKMeans();
        cluster.setOptions(new String[]{
            "-N", "2",
            "-A", "weka.core.ManhattanDistance"
        });
        
        cluster.buildClusterer(newData);
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(cluster);
        eval.evaluateClusterer(newData);
        System.out.println(eval.clusterResultsToString());  
//        for (int i = 0; i < newData.size(); i++) {
//            Instance instance = newData.get(i);
//            if (cluster.clusterInstance(instance) == 0) {
//                System.out.println(data.get(i).toString());
//            }
//        }
    }
    
}
