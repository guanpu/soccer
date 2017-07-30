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

$ node index.js
[2017-07-23 19:34:24.713] [ERROR] [default] - TypeError: Cannot read property 'trim' of undefined
    at Object.$.each (C:\Users\pguan\PlayGround\soccer\nodejs\libs\playerFetcher.js:36:68)
    at initialize.exports.each (C:\Users\pguan\PlayGround\soccer\nodejs\node_modules\cheerio\lib\api\traversing.js:300:24)
    at IncomingMessage.response.on.e (C:\Users\pguan\PlayGround\soccer\nodejs\libs\playerFetcher.js:31:48)
    at emitNone (events.js:91:20)
    at IncomingMessage.emit (events.js:185:7)
    at endReadableNT (_stream_readable.js:974:12)
    at _combinedTickCallback (internal/process/next_tick.js:80:11)
    at process._tickCallback (internal/process/next_tick.js:104:9)
[2017-07-23 19:34:24.724] [INFO] [default] - aboutToClose
Child closeing
Main closing