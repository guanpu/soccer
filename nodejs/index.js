/*
 * Entrance of soccer node module.
 */
const schedule = require("node-schedule");
const oddFetcher = require("./libs/oddFetcher");
const lineupFetcher = require("./libs/lineupFetcher");
const _ = require("underscore");
const child_process = require("child_process");
const worker = child_process.fork(`${__dirname}/libs/udpServer`);
const logger = require("./config/logger").logger;

var matchesToCatchReslt = [];
var matchesToFetchLineup = [];

function fetchOdd() {
    oddFetcher.getUpcomingBetlines().then(_.partial(emit, 1)).then(updateMatchData).catch(_.partial(emit, 4));
}
function fetchLineup() {

}
function fetchResult() {

}
/**
 * The data can be odd, lineup or result, depending on the eventType.
 * @param data
 */
function updateMatchData(eventData) {
    if(eventData.eventType == 1) {
        //TODO: record matches
    } else if(eventData.eventType == 2) {
        //TODO: clear the lineup fetcher
    } else if(eventData.eventType == 3) {
        //TODO: clear the match result
    }
}

function emit(type, payload) {
    const data = {
        eventType: type,
        data: payload
    };
    worker.send(data);
    return data;
}

function run() {
    //TODO: leverage node-schedule

}
process.on("uncaughtException", (err)=>{
    worker.send("terminate");
    logger.WARN(err);
});
run();