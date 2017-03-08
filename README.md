Inspired by [this project in kaggle](https://www.kaggle.com/airback/d/hugomathien/soccer/match-outcome-prediction-in-football)
Use the same database, but different model&learning schema try to achieve the same goal.

Plan:

1 Use javaml to classify the match from data.
2 Get the performance of a training data.
3 Classify the match with multi-classifier(labels) as enum
4 Labels include 
  a. Win,Draw,Defeat
  b. WinAsExpected, DefeatAsExpected, DrawAsExpected, WinAsUnExpected, DefeatAsUnExpected, DrawAsUnExpected

````
Dataset data = FileHandler.loadDataset(new File("devtools/data/iris.data"), 4, ",");
Classifier knn = new KNearestNeighbors(5);
knn.buildClassifier(data);
//Object predictedClassValue = knn.classify(inst);
````
Architecture:

1 A JAVA trained JavaMl core with Mysql data
2 A Nodejs odd/lineup fetcher
3 A nodejs communication module to trigger betting strategy
4 A Java messaging module to notify the customer(subscriber)
5 A java betting strategy module with self-improve feature