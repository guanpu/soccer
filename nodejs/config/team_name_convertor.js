/**
 * This file aims to make team names form database, lineup-web-site, odds-web-site unified.
 * Created by XiaoPu on 2017/4/10.
 */
const fs = require("fs");
const dbname = require("./fourleagueteams.json");
const lineupname = require('./lineup_team_names.json');
const _ = require("underscore");
const util = require("../parser/utils");

function reader() {
    fs.open("./result.txt","w",function(err,fd){
        _.mapObject(dbname,function(name,id){
            let main = getMainName(name);
            var arrays = _.filter(_.values(lineupname),(item)=>{
                let str2 = getMainName(item);
                return equal(main, str2);
            });
            if(arrays.length>0) {
                let toWrite = id + " : " + name + " : " + arrays.toString() + "\r\n";
                fs.appendFile(fd, toWrite, 'utf8', function (err) {
                    if (err) console.error(err);
                });
            } else {
                let toWrite = id + " : " + name + " : MANUAL CHECK\r\n";
                fs.appendFile("./downgraded_teams.txt", toWrite, 'utf8', function (err) {
                    if (err) console.error(err);
                });
            }

        });

    })
}
function getMainName(str) {
    var names = str.split(/\s/g);
    var main = _.max(names,(i)=>{return i.length;});
    return main;
}
function kickChar(str) {
    if(!!str) {
        return str.match(/\w/g).join("");
    }
}
function equal(str1, str2) {
    let s1 = str1.trim();
    let s2 = str2.trim();
    if(kickChar(s1).indexOf(kickChar(s2)) > -1) {
        return true;
    }
}
reader();