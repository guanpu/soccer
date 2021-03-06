/*
 * Entrance of soccer node module.
 */
const schedule = require("node-schedule");
const oddFetcher = require("./libs/oddFetcher");
const playerFetcher = require("./libs/playerFetcher");
const lineupFetcher = require("./libs/lineupFetcher");
const _ = require("underscore");
const child_process = require("child_process");
const worker = child_process.fork(`${__dirname}/libs/udpServer`);
const logger = require("./config/logger").logger;
var targetMatches = [];
var matchesToCatchResult = [];
var matchesToFetchLineup = [];

function fetchOdd() {
    oddFetcher.getUpcoming().then(updateMatchData).then(oddFetcher.getBetLines).then(_.partial(emit, 1)).catch(_.partial(emit, 4));
}

/**
 * Fetch the first lineup of hometeam, if the corresponding awayteam is right and the state is confirmed, then return.
 * Else, it's recognized as error and return.
 * @param matchId
 */
function fetchLineup(match) {
    lineupFetcher.getLineUp(match.homeTeam, match.awayTeam,match.tstamp).then((resultLineUp)=>{
        emit(2,resultLineUp);
        // setTimeout(()=>{worker.send("terminate");},5000);
        //TODO: uncomment this when test over
        removeLineupSchedule(match.matchId);
    }).catch((e)=>{
        logger.error(e);
        logger.info(`No confirmed lineup data found for the specific match ${match.matchId}, will try later`);
    });
}

function fetchResult(matchId) {
    oddFetcher.getResult(matchId).then(function(resultData){
        /**
         * result data should be a single instance, like one of below:
         *   {
    "matchId": 43696,
    "leagueId": 3,
    "homeTeam": "1. FC Union Berlin",
    "awayTeam": "1.FC Nürnberg",
    "matchDateTime": "2017-03-20T18:15:00.000Z",
    "result": "1",
    "tstamp": 1490033700
  },
         {
           "matchId": 43800,
           "leagueId": 31,
           "homeTeam": "FC Roskilde ",
           "awayTeam": "Hobro",
           "matchDateTime": "2017-03-20T18:00:00.000Z",
           "result": "2",
           "tstamp": 1490032800
         },
         {
           "matchId": 43833,
           "leagueId": 39,
           "homeTeam": "Pandurii Tg. Jiu",
           "awayTeam": "CS Municipal Studentesc Iasi ",
           "matchDateTime": "2017-03-20T17:30:00.000Z",
           "result": "X",
           "tstamp": 1490031000
         },
         */
        emit(3,resultData);
        removeResultSchedule(resultData.matchId);
    }).catch((e)=>{
        logger.info(`No match result found for the specific match ${e.message}, will try later`);
    });
}
/**
 * When a upcoming match data comes, we need to judge if it's new target, if so, add it to
 * the caches, and schedule for consequent data-fetcher job of it.
 * @param data looks like   [{
    "matchId": 43851,
    "leagueId": 26,
    "leagueName": "Australia A League",
    "homeTeam": "Western Sydney Wanderers ",
    "awayTeam": "Melbourne City ",
    "tstamp": 1490341800
  }]
 */
function updateMatchData(matches) {
    if(!_.isArray(matches)) {
        throw new Error("Returning data not an array");
    }
    var newTargets = _.reject(matches, (match)=>{
        return _.contains(targetMatches, match.matchId);
    });
    targetMatches = targetMatches.concat(_.pluck(newTargets, "matchId"));
    if(targetMatches.length>1000) {
        emit(4, new Error("Match Stack overflow!"));
    }
    _.each(newTargets, function(item){
        /**
         * From 60min before the match to 5min before the match fetch the confirmed lineup every 5 minutes.
         */
        let lineup_startTime = new Date(item.tstamp * 1000 - 60*60*1000);
        let lineup_endTime = new Date(item.tstamp * 1000 - 5*60*1000);
        var j = schedule.scheduleJob({ start: lineup_startTime, end: lineup_endTime, rule: '*/5 * * * *' }, function(){
            fetchLineup(item);
        });
        /**
         * From 24 hour after the match to 36 hour after the match fetch the results every 2 hours. 6 requests per match.
         */
        let result_startTime = new Date(item.tstamp * 1000 + 24*60*60*1000);
        let result_endTime = new Date(item.tstamp * 1000 + 36*60*60*1000);
        var k = schedule.scheduleJob({ start: result_startTime, end: result_endTime, rule: '0 */2 * * *' }, function(){
            fetchResult(item.matchId);
        });
        matchesToFetchLineup.push({
            matchId: item.matchId,
            schedule: j
        });
        matchesToCatchResult.push({
            matchId: item.matchId,
            schedule: k
        });
    });
    return newTargets;
}

function removeLineupSchedule(matchId) {
    //Cancel the schedule of fetching lineup for that match
    var match = _.find(matchesToFetchLineup,(item)=>{
        item.matchId = matchId;
    });
    match.schedule.cancel();
    //Remove the match from the to-fetch-lineup-list
    matchesToFetchLineup = _.filter(matchesToFetchLineup, (item)=>{
        item.matchId = matchId;
    });
}

function removeResultSchedule(matchId) {
    //Cancel the schedule
    var match = _.find(matchesToCatchResult,(item)=>{
        item.matchId = matchId;
    });
    match.schedule.cancel();
    //Remove the specific match from the to-fetch-result-list.
    matchesToCatchResult = _.filter(matchesToCatchResult, (item)=>{
        item.matchId = matchId;
    });
    //Remove the specific match from the target-match-list.
    targetMatches = _.without(targetMatches, matchId);
}

/**
 * Notify the worker-thread to broadcast data for JVM.
 * @param type
 * @param payload
 */
function emit(type, payload) {
    const data = {
        eventType: type,
        data: payload
    };
    worker.send(data);
}

function run() {
    //Run every day to fetch new upcoming matches.
    schedule.scheduleJob("5 1 */1 * *", fetchOdd);
    
    //TODO: uncomment this after test. Update player database every Wednesday
    //schedule.scheduledJob("1 1 * * 3", updatePlayer);
}
process.on("uncaughtException", (err)=>{
    worker.send("terminate");
    logger.WARN(err);
});
process.on("exit",()=>{
   console.log("Main closing");
});
// run();
playerFetcher.getPlayer(1,0).then((data)=>{
    logger.info(data);
    emit(5,data);
    setTimeout(()=>{worker.send("terminate");},10000);  
}).catch((e)=>{
    logger.error(e);
    worker.send("terminate");
})