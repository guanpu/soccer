/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.core;

import java.io.IOException;
import weka.classifiers.*;
import weka.classifiers.trees.*;
import weka.core.Instances;
import java.util.*;
import soccer.mis.PersistInstance;
import weka.classifiers.bayes.*;
import weka.classifiers.functions.Logistic;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.LogitBoost;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.*;
import weka.core.converters.ConverterUtils.DataSink;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.EnsembleSelection;
import java.io.File;
/**
 *
 * @author pguan
 */
public class SimpleClassifier {
    private PersistInstance loader;
    private static final String ARFF_STRING = "F:\\data\\football-data\\ranking\\teammodel.arff";


    public SimpleClassifier(PersistInstance loader) {
        this.loader = loader;
    }

    public void evaluate() throws IOException, Exception {
        Instances data = loader.buildInstances();
        NumericToNominal toNominal = new NumericToNominal();
        toNominal.setOptions(new String[] {
            "-R", "5,6,8,9"
        });
        toNominal.setInputFormat(data);
        data = Filter.useFilter(data, toNominal);
        data.setClassIndex(6);
        
//        DataSink.write(ARFF_STRING, data);
        
        EnsembleLibrary ensembleLib = new EnsembleLibrary();
        ensembleLib.addModel("weka.classifiers.trees.J48");
        ensembleLib.addModel("weka.classifiers.bayes.NaiveBayes");
        ensembleLib.addModel("weka.classifiers.functions.SMO");
        ensembleLib.addModel("weka.classifiers.meta.AdaBoostM1");
        ensembleLib.addModel("weka.classifiers.meta.LogitBoost");
        ensembleLib.addModel("classifiers.trees.DecisionStump");
        ensembleLib.addModel("classifiers.trees.DecisionStump");
        EnsembleLibrary.saveLibrary(new File("./ensembleLib.model.xml"), ensembleLib, null);
        EnsembleSelection model = new EnsembleSelection();
        model.setOptions(new String[]{
            "-L", "./ensembleLib.model.xml", // </path/to/modelLibrary>"-W", path+"esTmp", // </path/to/working/directory> - 
            "-B", "10", // <numModelBags> 
            "-E", "1.0", // <modelRatio>.
            "-V", "0.25", // <validationRatio>
            "-H", "100", // <hillClimbIterations> 
            "-I", "1.0", // <sortInitialization> 
            "-X", "2", // <numFolds>
            "-P", "roc", // <hillclimbMettric>
            "-A", "forward", // <algorithm> 
            "-R", "true", // - Flag to be selected more than once
            "-G", "true", // - stops adding models when performance degrades
            "-O", "true", // - verbose output.
            "-S", "1", // <num> - Random number seed.
            "-D", "true" // - run in debug mode 
        });
//        double resES[] = evaluate(ensambleSel);
//        System.out.println("Ensemble Selection\n"
//                + "\tchurn:     " + resES[0] + "\n"
//                + "\tappetency: " + resES[1] + "\n"
//                + "\tup-sell:   " + resES[2] + "\n"
//                + "\toverall:   " + resES[3] + "\n");
//        models.add(new J48());
//        models.add(new RandomForest());
//        models.add(new NaiveBayes());
//        models.add(new AdaBoostM1());
//        models.add(new Logistic());
//        models.add(new MultilayerPerceptron());
        
        
        int FOLDS = 5;
        Evaluation eval = new Evaluation(data);
//
//        for (Classifier model : models) {
            eval.crossValidateModel(model, data, FOLDS,
                    new Random(1), new Object[]{});
            System.out.println(model.getClass().getName() + "\n"
                    + "\tRecall:    " + eval.recall(1) + "\n"
                    + "\tPrecision: " + eval.precision(1) + "\n"
                    + "\tF-measure: " + eval.fMeasure(1));
            System.out.println(eval.toSummaryString());
//        }
//        LogitBoost cl = new LogitBoost();
//        cl.setOptions(new String[] {
//            "-Q", "-I", "100", "-Z", "4", "-O", "4", "-E", "4"
//        });
//        cl.buildClassifier(data);
//        Evaluation eval = new Evaluation(data);
//        eval.crossValidateModel(cl, data, 6, new Random(1), new Object[]{});
//        System.out.println(eval.weightedFMeasure());
//        System.out.println(cl.graph());
//        System.out.println(cl.globalInfo());

    }

    public static void main(String[] args) throws Exception {
        PersistInstance loader = new PersistInstance();
        SimpleClassifier ap = new SimpleClassifier(loader);
        ap.evaluate();
    }
    
}
