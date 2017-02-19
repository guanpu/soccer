Plan:
1. Use javaml to classify the match from data.
2. Get the performance of a training data.
3. Classify the match with multi-classifer(labels) as enum
4. Labels include 
  a. Win,Draw,Defeat
  b. WinAsExpected, DefeatAsExpected, DrawAsExpected, WinAsUnExpected, DefeatAsUnExpected, DrawAsUnExpected
````
Dataset data = FileHandler.loadDataset(new File("devtools/data/iris.data"), 4, ",");
Classifier knn = new KNearestNeighbors(5);
knn.buildClassifier(data);
//Object predictedClassValue = knn.classify(inst);
````




