2017-11 UPDATE: For some reason, this project has been moved to bitbucket, anyone who interest & want to collaborate can contact me.

Inspired by [this project in kaggle](https://www.kaggle.com/airback/d/hugomathien/soccer/match-outcome-prediction-in-football).

Use the same database, but different model&learning schema try to achieve the same goal.

Architecture:

1. A JAVA trained weka core with Mysql data
2. A Nodejs odd/lineup fetcher
3. A nodejs communication module to trigger betting strategy
4. A Java messaging module to notify the customer(subscriber)
5. A java betting strategy module with self-improve feature

Recent TODO:

1. Get lineup from http://www.teamfeed.co.uk, leverage cheerio to fetch the lineups before match.
2. Unify team name and player name between several datasource, problems might include:

+ remove attribute like "FC" in the team name
+ double valid name like 'athletic-club' vs 'athletic-bioble', need to manually check and repair this.
+ comparing with latin characters against English characters, like ...
