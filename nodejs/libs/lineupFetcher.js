/**
 * Created by XiaoPu on 2017/3/11.
 */
const util = require("../parser/utils");
const cheerio = require("cheerio");
const http = require("http");
const config = require("../config/config.json").resource.lineup;
const teamName = require("../config/lineup_team_names.json");

/**
 * Reject that if the lineup is not confirmed.
 * @param homeTeam
 * @param awayTeam
 * @param dueDate
 */
function getLineUp(homeTeam, awayTeam, dueDate) {
    return new Promise(function(resolve, rej) {
        let homeName = util.normalizeName(homeTeam);
        let awayName = util.normalizeName(awayTeam);
        let str = util.concatDateFormatForLineUp(new Date(dueDate*1000));
        let totalUrl = config + homeName;
        http.get(totalUrl, (res) => {
            const statusCode = res.statusCode;
            if (statusCode !== 200 && statusCode !== 304) {
                let error = new Error(`Request Failed with status ${codestatusCode}`);
                //noinspection JSUnresolvedFunction
                res.resume();
                rej(error);
            }
            res.setEncoding("utf8");
            let rawData = '';
            res.on("data", (chunk) => {
                rawData += chunk;
            });
            res.on("end", () => {
                try {
                    let $ = cheerio.load(rawData);
                    let homePlayers = [];
                    let awayPlayers = [];
                    //pickup the right div
                    let theDiv = $("div.pagination").next("div");
                    if (theDiv.find("h2>a").text().indexOf(teamName[awayName]) > 0 && theDiv.find("h2 span").text() == "Confirmed") {
                        if (theDiv.find("div.pitch-header").text().indexOf(str) > 0) {
                            theDiv.find("div.home").children().each(function (index, element) {
                                let player = {
                                    no: $(element).find("div.player-no").text(),
                                    name: util.normalizeName($(this).find("div.player-name").text())
                                };
                                homePlayers.push(player);
                            });
                            theDiv.find("div.away").children().each(function (index, element) {
                                let player = {
                                    no: $(element).find("div.player-no").text(),
                                    name: util.normalizeName($(this).find("div.player-name").text())
                                };
                                awayPlayers.push(player);
                            });
                            resolve({
                                homePlayers: homePlayers,
                                awayPlayers: awayPlayers
                            });
                        }
                    }
                    rej(new Error("Not found the correct match"));
                } catch (e) {
                    rej(e);
                }
            });
        }).on('error', (e) => {
            rej(e);
        });
    });
}

module.exports = {
    getLineUp: getLineUp
};