/**
 * Created by XiaoPu on 2017/3/11.
 * [
  {
     "name": "Spain Primera Division",
     "leagueId": 9
  },
 {
   "name": "England Premier League",
   "leagueId": 7
 },
 {
   "name": "Germany Bundesliga",
   "leagueId": 2
 },
 {
   "name": "Italy Serie A",
   "leagueId": 10
 }
 ]
 */
const https = require("https");
const url = require("url");
const config = require("../config/config.json").resource.odd;
const parser = require("../parser/odd_parser");
const _ = require("underscore");
const logger = require("../config/logger").logger;

//set authentication header
var header = {"Accept": "application/json"};
header[config.auth_key] = config.auth_value;
var baseOptions = {
    headers: header,
    hostname: config.hostname,
};

/**
 * Get the upcoming matches which belong to the leagues I get interest on.
 * @returns {Promise}
 */
function getUpcoming() {
    var promise = new Promise(function(resolve,rej){
        /**
         * since it use get() insteadOf request() that avoid manually
         * call req.end(), before reject this promise, we have to manually
         * invoke res.resume() so that resources can be released, like we
         * have do req.end().
         */
        https.get(_.extend(baseOptions,{"path": "/upcoming"}),(res)=>{
            const statusCode = res.statusCode;
            if(statusCode!==200&&statusCode!==304) {
                let error = new Error(`Request Failed with status ${codestatusCode}`);
                //noinspection JSUnresolvedFunction
                res.resume();
                rej(error);
            }
            res.setEncoding("utf8");
            let rawData = '';
            res.on("data", (chunk)=>{
                rawData += chunk;
            });
            res.on("end", ()=>{
               try{
                   let parsedData = JSON.parse(rawData);
                   let matches = parser.filter(parsedData);
                    resolve(matches);
               }catch(e) {
                    rej(e);
               }
            });
        }).on('error',(e)=>{
            rej(e);
        })
    });
    return promise;
}

/**
 * Taking an array of matches as input, output the array of odds as a promise.
 * @param data
 * @returns {Promise.<*>}
 */
function getBetLines(data) {
    let promises = [];
    _.each(data,function(item){
        let p = new Promise(function(resolve,reject){
            https.get(_.extend(baseOptions,{"path": "/matches/"+item.matchId}),(res)=>{
                const statusCode = res.statusCode;
            if(statusCode!==200 && statusCode!==304) {
                let error = new Error(`Request Failed with status code ${statusCode} when fetch ${item.matchId}`);
                res.resume();
                reject(error);
            }
            res.setEncoding("utf8");
            let rawData = '';
            res.on("data", (chunk)=>{
                rawData += chunk;
            });
            res.on("end", ()=>{
                try{
                    let parsedData = JSON.parse(rawData);
                    let matches = parser.mergeOdds(parsedData);
                    let wrapMatchData = {
                        "matchId": item.matchId,
                        "data": matches
                    };
                    resolve(wrapMatchData);
                }catch(e) {
                    reject(e);
                }
            });
            }).on("error",(e)=>{
                reject(e);
            });
        });
        promises.push(p);
    });
    return Promise.all(promises);
}

/**
 * TODO: Make it real singleton, i.e. can only run once if other instance not finished.
 */
function getUpcomingBetlines() {
    return getUpcoming().then(getBetLines);
}
module.exports = {
 getUpcomingBetlines: getUpcomingBetlines
};

























