/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.core;

import java.io.IOException;
import weka.core.Instances;
import weka.core.converters.DatabaseLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

/**
 *
 * @author pguan
 */
public class MyMatchLoader {
    private final DatabaseLoader loader;
    private static final String URL="jdbc:mysql://localhost:3306/football";
    private static final String QUERY_STRING = "select IWH,IWD,IWA,B365H,B365D,B365A,IFNULL((home_team_goal-away_team_goal)/abs(home_team_goal-away_team_goal),0) as result from matches where B365A >1.40 and B365A < 6.0 and B365H> 1.40 and B365H<6.0";
    
    public MyMatchLoader() throws Exception {
        this.loader = new DatabaseLoader();
        loader.setUrl(URL);
        loader.setUser("root");
        loader.setPassword("root");
        loader.setQuery(QUERY_STRING);
    }
    
    public Instances getInstances() throws IOException {
        return loader.getDataSet();        
    }
    
    public void test() throws IOException, Exception {
        Instances instances = loader.getDataSet();
        NumericToNominal nm = new NumericToNominal();
        nm.setOptions(new String[]{"-R", "last"});
        nm.setInputFormat(instances);
        instances = Filter.useFilter(instances, nm);
        instances.setClassIndex(instances.numAttributes() - 1);
        System.out.println(instances.toSummaryString());
    }
    
    public static void main(String[] args) throws Exception {
        MyMatchLoader myloader = new MyMatchLoader();
        myloader.test();      
    }
}
