const cheerio = require("cheerio");
const https = require("https");

function getPlayer(offset) {
    var url = "https://sofifa.com/players?offset=" + offset;    
    return new Promise(function(resolve,reject){
        var rawData;
        https.get(url,(response)=>{
            if(response.statusCode != 200 || response.statusCode != 304) {
                reject(new Error(response));
            }
            response.on("data", (chunk)=>{
                rawData += chunk;
            });
            response.on("end", ()=>{
                try{
                    let $ = cheerio.load(rawData);
                    let players = [];
                    $("table.persist-area tr").each((index, elem)=>{
                        let player = {};
                        player.name = $(this).find("td").eq(2).find("a[rel!=nofollow]").attr("title").trim();
                        $(this).children("td.col").each((idx,ele)=>{
                            if(elem)
                        })
                        player.age = $(this).find("div.col-ae").text().trim();
                        player.overall = $(this).find("div.col-").text().trim();
                    });
                } catch (e) {
                    reject(e);
                }
            });
        }).on("error", (e)=>{
            reject(e);
        })
    });
    
}