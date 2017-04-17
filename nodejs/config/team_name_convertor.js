/**
 * This file aims to make team names form database, lineup-web-site, odds-web-site unified.
 * Created by XiaoPu on 2017/4/10.
 */
const fs = require("fs");
const dbname = require("./team_names_db.json");
const lineupname = require('./lineup_team_names.json');
const _ = require("underscore");
const rl = require("readline");
function run() {
    let linereader = rl.createInterface({
        input: fs.createReadStream("./result.txt",{
            flags: 'r',
            encoding: "utf8"
        })
    });
    linereader.on("line",(data)=>{
        var ary = data.split(":");
        if(ary.length>2) {
            let tmp = ary[2].trim();
            let toWrite = "\"" + dbname[ary[0].trim()] + "\": \"" + tmp +"\",\r\n";
            fs.appendFile("./db_to_lineup.json", toWrite, 'utf8', function (err) {
                if (err) console.error(err);
            });
        }
    });
}
run();



//
// function reader() {
//     fs.open("./result.txt","w",function(err,fd){
//         _.mapObject(dbname,function(name,id){
//             let main = getMainName(name);
//             var arrays = _.filter(_.values(lineupname),(item)=>{
//                 let str2 = getMainName(item);
//                 return equal(main, str2);
//             });
//             if(arrays.length>0) {
//                 let toWrite = id + " : " + name + " : " + arrays.toString() + "\r\n";
//                 fs.appendFile(fd, toWrite, 'utf8', function (err) {
//                     if (err) console.error(err);
//                 });
//             } else {
//                 let toWrite = id + " : " + name + " : MANUAL CHECK\r\n";
//                 fs.appendFile("./downgraded_teams.txt", toWrite, 'utf8', function (err) {
//                     if (err) console.error(err);
//                 });
//             }
//
//         });
//
//     })
// }
// function getMainName(str) {
//     var names = str.split(/\s/g);
//     var main = _.max(names,(i)=>{return i.length;});
//     return main;
// }
// function kickChar(str) {
//     if(!!str) {
//         return str.match(/\w/g).join("");
//     }
// }
// function equal(str1, str2) {
//     let s1 = str1.trim();
//     let s2 = str2.trim();
//     if(kickChar(s1).indexOf(kickChar(s2)) > -1) {
//         return true;
//     }
// }
// reader();