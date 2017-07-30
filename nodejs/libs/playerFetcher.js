const $ = require("cheerio");
const https = require("https");
const mysql = require("mysql");
const config = require("../config/config.json").db;
const logger = require("../config/logger").logger;
const _ = require("underscore");
const fs = require("fs");
let connectionConfig;
if(process.env.PRDO == 'on') {
    connectionConfig = config.prod;
} else {
    connectionConfig = config.test;
}
let pool;
/**
 * Fetch from sofifa, every page would have 80 players, so the offset should be n*80.
 * @param {int} type : 1 for create, 2 for update
 * @param {} offset
 */
function getPlayer(type, offset) {
    let buildUrl;
    if(type == 1) {
        buildUrl = "https://sofifa.com/players?offset=";
    } else {
        buildUrl = "https://sofifa.com/players/updated?offset=";
    }
    let url = buildUrl + offset;
    return new Promise(function(resolve, reject) {
        https.get(url, (response) => {
            const { statusCode } = response;
            let error;
            if (statusCode !== 200) {
                error = new Error('Request Failed.\n' + `Status Code: ${statusCode}`);
            }
            if (error) {
                response.resume();
                reject(error);
            }
            let rawData = "";
            response.on("data", (chunk) => {
                rawData += chunk;
            });
            response.on("end", () => {
                try {
                    //Not sure why, directly load won't success, so remove useless html
                    rawData = rawData.substring(rawData.indexOf("<tbody>"),rawData.indexOf("</tbody>"));
                    let dateString =  "\'" + (new Date()).toISOString().substr(0,10)+" 00:00:00\'";
                    var players = [];                    
                    $("tr", rawData).each((index, elem) => {
                        let player = {};
                        player.player_date = dateString;
                        player.positioning = $(elem).find("div.col-oa>span").text().trim();                        
                        let temp_ele = $(elem).find("td").eq(1).find("a[rel!=nofollow]");
                        try{
                            player.team_api_id = $(elem).find("div.col-name>figure").siblings("a").attr("href").substr(6).trim();
                            player.player_fifa_api_id = temp_ele.attr("href").substr(8);                        
                        } catch (e) {
                        }
                        // player.player_name = temp_ele.attr("title").trim();
                        // player.age = $(elem).find("div.col-ae").text().trim();
                        player.potential = $(elem).find("div.col-pt>span").text().trim();
                        player.acceleration = $(elem).find("div.col-ac>span").text().trim();
                        player.aggression = $(elem).find("div.col-ar>span").text().trim();
                        player.agility = $(elem).find("div.col-ag>span").text().trim();
                        player.attacking_work_rate ="\'" + $(elem).find("div.col-aw").text().trim() + "\'";
                        player.balance = $(elem).find("div.col-ba>span").text().trim();
                        player.ball_control = $(elem).find("div.col-bl>span").text().trim();
                        player.crossing = $(elem).find("div.col-cr>span").text().trim();
                        player.curve = $(elem).find("div.col-cu>span").text().trim();
                        player.defensive_work_rate ="\'" + $(elem).find("div.col-dw").text().trim() + "\'";
                        player.dribbling = $(elem).find("div.col-dr>span").text().trim();
                        player.finishing = $(elem).find("div.col-fi>span").text().trim();
                        player.free_kick_accuracy = $(elem).find("div.col-fr>span").text().trim();
                        player.heading_accuracy = $(elem).find("div.col-he>span").text().trim();
                        player.interceptions = $(elem).find("div.col-in>span").text().trim();
                        player.jumping = $(elem).find("div.col-ju>span").text().trim();
                        player.long_passing = $(elem).find("div.col-lo>span").text().trim();
                        player.long_shots = $(elem).find("div.col-ln>span").text().trim();
                        player.marking = $(elem).find("div.col-ma>span").text().trim();
                        player.penalties = $(elem).find("div.col-pe>span").text().trim();
                        player.positioning = $(elem).find("div.col-po>span").text().trim();
                        player.reactions = $(elem).find("div.col-re>span").text().trim();
                        player.short_passing = $(elem).find("div.col-sh>span").text().trim();
                        player.shot_power = $(elem).find("div.col-so>span").text().trim();
                        player.sliding_tackle = $(elem).find("div.col-sl>span").text().trim();
                        player.sprint_speed = $(elem).find("div.col-sp>span").text().trim();
                        player.stamina = $(elem).find("div.col-st>span").text().trim();
                        player.standing_tackle = $(elem).find("div.col-sa>span").text().trim();
                        player.strength = $(elem).find("div.col-sr>span").text().trim();
                        player.vision = $(elem).find("div.col-vi>span").text().trim();
                        player.volleys = $(elem).find("div.col-vo>span").text().trim();
                        players.push(player);                     
                    });
                    if(players.length==0) {
                        reject(new Error("Can't query(any more)"));
                    }else {
                        resolve(players);
                    }
                } catch (e) {
                    reject(e);
                }
            });
        }).on("error", (e) => {
            reject(e);
        })
    });
}
/**
 * TODO: Don't use a monolithic loop to update database.
 * Instead, assume there be network or database error, and record down the successfully updated ones
 * and then retry from the new ones.
 */
function buildDatabase() {
    pool = mysql.createPool(connectionConfig);
    fetchMore(1,0);
}

function fetchMore(type, index) {
    getPlayer(type, index).then(insertBatch).then((data)=>{
        if(data.length==80){
            index+=80;
            fetchMore(type, index);
        } else {
            throw new Error("Manually stop");
        }
    }).catch((err)=>{
        logger.info("Stop looping because of ", err);
        pool.end();
    })
}

function insertBatch(data) {
    let promises = [];
    data.forEach(function(ele){
        promises.push(insertOne(ele));
    });
    return Promise.all(promises);
}


function insertOne(data) {
    let str = "Insert into player_attributes set ";
    let attrs = [];

    for(let prop in data) {
        if(data[prop]===undefined || data[prop]==="" || data[prop]===null) {
            continue;
        }
        let temp = prop + "=" +data[prop];
        attrs.push(temp);
    }
    var toAdd = attrs.join(",");
    if(!!toAdd) {
        return new Promise((res,rej)=>{
            pool.getConnection(function(err, connection) {
                if(err) {
                    rej(err);
                }
                str = str + toAdd;
                connection.query(str,function(error,results){
                    connection.commit((err)=>{
                        connection.release();                    
                        if(error) {
                            rej(error);
                        }
                        res(results);                    
                    });
                });
            });
        });
    } else {
        return Promise.reject(new Error("Adding empty data"));
    }
}

function updateDatabase() {
    pool = mysql.createPool(connectionConfig);    
    fetchMore(0,0);
}
buildDatabase();
module.exports = {
    buildDatabase: buildDatabase,
    updateDatabase: updateDatabase
};
