/**
 * Created by XiaoPu on 2017/4/11.
 */
const schedule = require("node-schedule");
const oddFetcher = require("./libs/oddFetcher");
const fs = require("fs");
const _ = require("underscore");
const logger = require("./config/logger").logger;

var names = [];
function fetchOdd() {
    oddFetcher.getUpcoming().then(updateMatchData).catch((err)=>{
        logger.warn(err);
    });
}
function updateMatchData(matches) {
    names = names.concat(_.pluck(matches, "homeTeam"));
    names = names.concat(_.pluck(matches, "awayTeam"));
    let str = _.uniq(names).join("\n");
    fs.writeFileSync("./teamnames.txt",str);
    logger.info((new Date()).toString() + " updated" );
    return;
}
function run() {
    schedule.scheduleJob("0 */1 * * *", fetchOdd);
}
run();
