/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.core;

import java.io.IOException;
import java.io.*;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import weka.filters.*;
import weka.filters.unsupervised.attribute.Remove;
/**
 *
 * @author pguan
 */
public class MyPlayerLoader {
    private final CSVLoader loader;

    public MyPlayerLoader() throws Exception {
        this.loader = new CSVLoader();
        loader.setOptions(new String[]{
            "-H",
            "-R", "2-23",
            "-N", "last"
        });
        loader.setSource(new File("F:\\codes\\workspace\\soccer\\tmp\\model1.csv"));
    }

    public Instances getInstances() throws IOException, Exception {
        Instances instances = loader.getDataSet();
        Remove rm = new Remove();
        int[] indicies = new int[]{
            0,23,24,25,26,27,28
        };
        rm.setAttributeIndicesArray(indicies);
        rm.setInputFormat(instances);
        instances = Filter.useFilter(instances, rm);
        instances.setClassIndex(instances.numAttributes() - 1);
        return instances;
    }

    public void test() throws IOException, Exception {
        Instances instances = getInstances();

        System.out.println(instances.toSummaryString());
    }

    public static void main(String[] args) throws Exception {
        MyPlayerLoader myloader = new MyPlayerLoader();
        myloader.test();
    }
    
}
